package ro.cmm.dao.inmemory;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ro.cmm.dao.UserDAO;
import ro.cmm.domain.User;
import ro.cmm.domain.Role;

import java.util.*;

/**
 * Created by Joseph Saturday, 15.04.2017 at 12:56.
 */
@Component
public class IMUserDAO extends IMBaseDAO<User> implements UserDAO {

    private Role role;
    private long id;

    @Override
    public Collection<User> searchByName(String query) {
        if (StringUtils.isEmpty(query)) {
            return getAll();
        }

        Collection<User> all = new LinkedList<User>(getAll());
        for (Iterator<User> it = all.iterator(); it.hasNext();) {
            User emp = it.next();
            String ss = emp.getFirstName() + " " + emp.getLastName();
            if (!ss.toLowerCase().contains(query.toLowerCase())) {
                it.remove();
            }
        }
        return all;
    }

    public Collection<User> getAllSellers(){
        Collection<User> sellers = new LinkedList<>();
        for (User user : getAll()){
            if (user.getRole().equals(Role.SELLER)){
                sellers.add(user);
            }
        }
        return sellers;
    }

    public Collection<User> getAllBuyers(){
        Collection<User> buyers = new LinkedList<>();
        for (User user : getAll()){
            if (user.getRole().equals(Role.BUYER)){
                buyers.add(user);
            }
        }
        return buyers;
    }

    public User findByUsername(String userName){
        for (User user : getAll()){
            if (user.getUserName().equals(userName)){
                return user;
            }
        }
        return null;
    }

    public boolean isRegistered(String userName, String password){
        for (User user : getAll()){
            if (user.getUserName().equals(userName)&&user.getPassword().equals(password)){
               role=user.getRole();
               id=user.getId();
                return true;
            }
        }
        return false;
    }

    public Role getRole() {
        return role;
    }

    public long getId() {
        return id;
    }
}
