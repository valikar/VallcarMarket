package ro.cmm.dao;

import ro.cmm.domain.Role;
import ro.cmm.domain.User;

import java.util.Collection;

/**
 * Created by Joseph Saturday, 15.04.2017 at 12:57.
 */
public interface UserDAO extends BaseDAO<User> {

    Collection<User> searchByName(String query);

    User findByUsername(String userName);

    boolean isRegistered(String userName, String password);

    Role getRole();

    long getId();

    String getFullName();

    Collection<User> getAllSellers();

    Collection<User> getAllBuyers();

    void addBookmark(long id);

    void deleteBookmark(long id);

    Collection<Long> getBookmarkList(long id);
}
