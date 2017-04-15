package ro.cmm.dao.inmemory;

import org.springframework.util.StringUtils;
import ro.cmm.dao.UserDAO;
import ro.cmm.domain.User;
import ro.cmm.domain.login.AccountType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Joseph Saturday, 15.04.2017 at 12:56.
 */
public class IMUserDAO extends IMBaseDAO<User> implements UserDAO {

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
            if (user.getRole().equals(AccountType.SELLER)){
                sellers.add(user);
            }
        }
        return sellers;
    }

    public Collection<User> getAllBuyers(){
        Collection<User> buyers = new LinkedList<>();
        for (User user : getAll()){
            if (user.getRole().equals(AccountType.BUYER)){
                buyers.add(user);
            }
        }
        return buyers;
    }
}
