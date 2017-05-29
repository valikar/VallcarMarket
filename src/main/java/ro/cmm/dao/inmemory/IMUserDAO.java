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
public class IMUserDAO extends IMBaseDAO<User> implements UserDAO {

    private Map<Long,Collection<Long>> bookmarks = new HashMap<>();

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

    @Override
    public User changePassword(User user) {
        update(user);
        return user;
    }

    @Override
    public Collection<User> getAllSellers(){
        Collection<User> sellers = new LinkedList<>();
        for (User user : getAll()){
            if (user.getRole().equals(Role.SELLER)){
                sellers.add(user);
            }
        }
        return sellers;
    }

    @Override
    public Collection<User> getAllBuyers(){
        Collection<User> buyers = new LinkedList<>();
        for (User user : getAll()){
            if (user.getRole().equals(Role.BUYER)){
                buyers.add(user);
            }
        }
        return buyers;
    }

    @Override
    public User findByUsername(String userName){
        for (User user : getAll()){
            if (user.getUserName().equals(userName)){
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean isRegistered(String userName, String password){
        for (User user : getAll()){
            if (user.getUserName().equals(userName)&&user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void addBookmark(long carId, long userId) {
        if (bookmarks.get(userId) != null) {
            Collection<Long> updatedList=bookmarks.get(userId);
            boolean inList = false;
            for (Long l : updatedList){
                if (l==carId){
                    inList=true;
                }
            }
            if (!inList){
            updatedList.add(carId);
            bookmarks.replace(userId,updatedList);}
        }else {
            Collection<Long> list = new LinkedList<>();
            list.add(carId);
            bookmarks.put(userId,list);
        }
    }

    @Override
    public void deleteBookmark(long carId, long userId) {
        if (bookmarks.get(userId) != null) {
            bookmarks.get(userId).remove(carId);
        }
    }

    @Override
    public Collection<Long> getBookmarkList(long id) {
        return bookmarks.get(id);
    }
}
