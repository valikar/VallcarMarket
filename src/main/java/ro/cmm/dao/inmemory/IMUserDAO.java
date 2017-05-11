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

    private Role role;
    private long id;
    private String fullName;
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
               role=user.getRole();
               id=user.getId();
               fullName = user.getFirstName() + " " +user.getLastName();
                return true;
            }
        }
        return false;
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
    public void addBookmark(long id) {
        if (bookmarks.get(this.id) != null) {
            Collection<Long> updatedList=bookmarks.get(this.id);
            boolean inList = false;
            for (Long l : updatedList){
                if (l==id){
                    inList=true;
                }
            }
            if (!inList){
            updatedList.add(id);
            bookmarks.replace(this.id,updatedList);}
        }else {
            Collection<Long> list = new LinkedList<>();
            list.add(id);
            bookmarks.put(this.id,list);
        }
    }

    @Override
    public void deleteBookmark(long id) {
        if (bookmarks.get(this.id) != null) {
            bookmarks.get(this.id).remove(id);
        }
    }

    @Override
    public Collection<Long> getBookmarkList(long id) {
        return bookmarks.get(id);
    }
}
