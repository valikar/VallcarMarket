package ro.cmm.dao.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import ro.cmm.dao.UserDAO;
import ro.cmm.domain.Role;
import ro.cmm.domain.User;
import ro.cmm.service.LoginService;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Joseph Wednesday, 10.05.2017 at 21:22.
 */
public class JdbcTemplateUserDAO implements UserDAO {

    private JdbcTemplate jdbcTemplate;
    private Role role;
    private long id;
    private String fullName;

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcTemplateUserDAO.class);


    private String userDetailsForQuery ="SELECT u.id, " +
                                        "u.first_name, "+
                                        "u.last_name, "+
                                        "u.email, "+
                                        "u.phone_number, "+
                                        "r.role_name, "+
                                        "u.password, "+
                                        "u.password_validation "+

                                        "FROM users u JOIN roles r ON u.role_id = r.id ";

    public JdbcTemplateUserDAO (DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<User> getAll() {
        String query = userDetailsForQuery;
        Collection<User> users =jdbcTemplate.query(query,new UserResultSetExtractor());
        LOGGER.info("Getting "+users.size()+ " user[s]");
        return users;
    }

    @Override
    public User findById(Long id) {
        String query = userDetailsForQuery + "WHERE id= ?";
        Collection<User> users = jdbcTemplate.query(query,new UserResultSetExtractor(),id);
        User user;
        if (users.size()!=1){
            user=null;
            LOGGER.info("No user found with id: "+id);
        }else {
            user=users.iterator().next();
            LOGGER.info("Found user with this email: "+user.getUserName()+" under "+id+" id");
        }
        return user;
    }

    @Override
    public User update(User model) {
        String sql = "";
        Long newId = null;
        if (model.getId()>0){
            sql = "UPDATE users SET first_name=?,"+
                                    "last_name=?,"+
                                    "email=?,"+
                                    "phone_number=?,"+
                                    "role_id=(SELECT id FROM roles WHERE role_name =?),"+
                                    "password=?,"+
                                    "password_validation=?"+
                                    "WHERE id=? returning id";
            newId = jdbcTemplate.queryForObject(sql,new Object[]{
                    model.getFirstName(),
                    model.getLastName(),
                    model.getUserName(),
                    model.getPhoneNumber(),
                    model.getRole().name(),
                    model.getPassword(),
                    model.getPasswordValidation(),
                    model.getId()
            },new RowMapper<Long>(){
                public Long mapRow(ResultSet resultSet, int i) throws SQLException{
                    return resultSet.getLong(1);
                }
            });
            LOGGER.info("Update on user with this email: "+model.getUserName());
        }else {
            LOGGER.info(model.getRole().name());
            sql="INSERT INTO users (first_name, last_name, email, phone_number, role_id, password, password_validation) "+
                    "VALUES ( ?,"+
                            " ?,"+
                            " ?,"+
                            " ?,"+
                            " (SELECT id FROM roles WHERE role_name=?),"+
                            " ?,"+
                            " ?"+
                            " ) returning id";
            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    model.getFirstName(),
                    model.getLastName(),
                    model.getUserName(),
                    model.getPhoneNumber(),
                    model.getRole().name(),
                    model.getPassword(),
                    model.getPasswordValidation()
            }, new RowMapper<Long>() {
                @Override
                public Long mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getLong(1);
                }
            });
            LOGGER.info("Added user with email: "+model.getUserName());
        }
        model.setId(newId);
        return model;
    }


    @Override
    public boolean delete(User model) {
        boolean delete = jdbcTemplate.update("DELETE FROM users WHERE id=?", model.getId())>0;
        if (delete){
            LOGGER.info("Deleted user with email: "+model.getUserName());
            return true;
        }else {
            LOGGER.info("Failed to delete user with email: "+model.getUserName());
            return false;
        }
    }

    @Override
    public User findByUsername(String userName) {
        String query = userDetailsForQuery + "WHERE email= ?";
        Collection<User> users = jdbcTemplate.query(query,new UserResultSetExtractor(),userName);
        User user;
        if (users.size()!=1){
            LOGGER.info(userName+" is free to use");
            user=null;
        }else {
            LOGGER.info(userName+" is taken");
            user=users.iterator().next();

        }
        return user;
    }

    @Override
    public boolean isRegistered(String userName, String password) {
        String query = userDetailsForQuery + "WHERE email= ? AND password= ?";
        Collection<User> users = jdbcTemplate.query(query,new UserResultSetExtractor(),userName,password);
        User user;
        if (users.size()!=1){
            LOGGER.info(userName+" is not registered");
            return false;
        }else {
            LOGGER.info(userName+" is registered");
            user=users.iterator().next();
            id=user.getId();
            role=user.getRole();
            fullName=user.getFirstName()+" "+user.getLastName();
            return true;
        }
    }

    @Override
    public Role getRole() {
        return role;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public Collection<User> getAllSellers() {
        String query = userDetailsForQuery + "WHERE role_id= 3";
        Collection<User> users = jdbcTemplate.query(query,new UserResultSetExtractor());
        return users;
    }

    @Override
    public Collection<User> getAllBuyers() {
        String query = userDetailsForQuery + "WHERE role_id= 2";
        Collection<User> users = jdbcTemplate.query(query,new UserResultSetExtractor());
        return users;
    }

    @Override
    public void addBookmark(long id) {
    String query = "SELECT * FROM bookmarks WHERE user_id= ?";
    String sql="";
    Collection<Long> bookmarks = jdbcTemplate.query(query,new BookmarkResultSetExtractor(),this.id);
        boolean inList = false;
        for (Long l : bookmarks) {
            if (l == id) {
                inList = true;
            }
        }
        if (!inList){
        sql= "INSERT INTO bookmarks (user_id,car_id) "+
                "VALUES ( ? , ? )";
        jdbcTemplate.update(sql,this.id,id);
        }
    }

    @Override
    public void deleteBookmark(long id) {
    String sql = "DELETE FROM bookmarks WHERE user_id = ? AND car_id=?";
    jdbcTemplate.update(sql,this.id,id);
    }

    @Override
    public Collection<Long> getBookmarkList(long id) {
        String query = "SELECT * FROM bookmarks WHERE user_id= ?";
        Collection<Long> bookmarks = jdbcTemplate.query(query,new BookmarkResultSetExtractor(),id);
        return bookmarks;
    }

    @Override
    public Collection<User> searchByName(String query) {
        return null;
    }

    private static class UserResultSetExtractor implements ResultSetExtractor<Collection<User>>{

        @Override
        public Collection<User> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            Map<Long,User> users = new HashMap<>();

            while (resultSet.next()){
                if (!users.keySet().contains(resultSet.getLong("id"))) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setLastName(resultSet.getString("last_name"));
                    user.setUserName(resultSet.getString("email"));
                    user.setPhoneNumber(resultSet.getInt("phone_number"));
                    user.setRole(Role.valueOf(resultSet.getString("role_name")));
                    user.setPassword(resultSet.getString("password"));
                    user.setPasswordValidation(resultSet.getString("password_validation"));
                    
                    users.put(user.getId(), user);
                }
            }
            return users.values();
        }
    }

    private static class BookmarkResultSetExtractor implements ResultSetExtractor<Collection<Long>> {

        @Override
        public Collection<Long> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            Collection<Long> bookmarks = new LinkedList<>();
            while (resultSet.next()){
                bookmarks.add(resultSet.getLong("car_id"));
            }
            return bookmarks;
        }
    }

}
