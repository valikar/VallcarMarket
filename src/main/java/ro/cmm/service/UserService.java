package ro.cmm.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ro.cmm.dao.inmemory.IMUserDAO;
import ro.cmm.domain.User;

import java.util.*;

/**
 * Created by Joseph Saturday, 15.04.2017 at 01:57.
 */
@Service
public class UserService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private IMUserDAO dao;

    public User getById(long id) {
        LOGGER.debug("Getting user with id: " + id);
        return dao.findById(id);
    }

    public void save(User user) throws ValidationException {
        LOGGER.debug("Saving: " + user);
        validate(user);
        dao.update(user);
    }

    public boolean delete(long id) {
        LOGGER.debug("Deleting user with id: " + id);
        User u = dao.findById(id);
        if (u != null) {
            dao.delete(u);
            return true;
        }
        return false;
    }


    public Collection<User> search(String query) {
        LOGGER.debug("Searching for " + query);
        return dao.searchByName(query);
    }

    public boolean verifyUsername(String userName) {
        LOGGER.debug("Verifying username : "+ userName);
        if (dao.findByUsername(userName)!=null){
        User user= dao.findByUsername(userName);
        if (user.getUserName().equals(userName)) {
            LOGGER.debug("This username : "+ userName+" is taken");
            return false;
            }
        }
        LOGGER.debug("This username : "+ userName+" is free");
        return true;
    }

    public boolean isRegistered(String userName, String password){
        LOGGER.debug("Verifying if this user: "+userName+" with this password "+password+" is registered");
        if (dao.isRegistered(userName,password))
            return true;
        else
            return false;
    }

    public Collection<User> listAll() {
        LOGGER.debug("Listing all users ");
        return dao.getAll();
    }

    public Collection<User> listAllSellers() {
        LOGGER.debug("Listing all sellers ");
        return dao.getAllSellers();
    }

    public Collection<User> listAllBuyers() {
        LOGGER.debug("Listing all buyers ");
        return dao.getAllBuyers();
    }

    private void validate(User user) throws ValidationException {
        LOGGER.debug("Validating user with username: "+user.getUserName());
        List<String> errors = new LinkedList<String>();

        if (StringUtils.isEmpty(user.getUserName())) {
            errors.add("Empty Username");
        }

        if (StringUtils.isEmpty(user.getPassword())) {
            errors.add("Empty Password");
        }

        if (StringUtils.isEmpty(user.getFirstName())) {
            errors.add("Empty First Name");
        }

        if (StringUtils.isEmpty(user.getLastName())) {
            errors.add("Empty Last Name");
        }

        if (StringUtils.isEmpty(user.getRole())) {
            errors.add("Empty Role");
        }

        if (StringUtils.isEmpty(user.getPassword())) {
            errors.add("Empty password");
        }

        if (StringUtils.isEmpty(user.getPasswordValidation())) {
            errors.add("Empty password validation");
        }

        if (!StringUtils.isEmpty(user.getPassword())&&!StringUtils.isEmpty(user.getPasswordValidation())) {
            if (!user.getPassword().equals(user.getPasswordValidation())) {
                errors.add("Passwords don`t match");
            }
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[]{}));
        }
    }

    public void addBookmark(long id) {
        LOGGER.debug("Adding car with id: "+id+" to bookmark list of the user with this id: "+dao.getId());
        dao.addBookmark(id);
    }

    public void deleteBookmark(long id){
        LOGGER.debug("Deleting car with id: "+id+" from bookmark list of the user with this id: "+dao.getId());
        dao.deleteBookmark(id);
    }
    public Collection<Long> getBookmarks(long id){
        LOGGER.debug("Getting bookmark list of the user with this id: "+id);
        return dao.getBookmarkList(id);
    }

    public void setDao(IMUserDAO dao) {
        this.dao = dao;
    }

    public IMUserDAO getDao() {
        return dao;
    }
}
