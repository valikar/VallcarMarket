package ro.cmm.dao;

import ro.cmm.domain.User;

import java.util.Collection;

/**
 * Created by Joseph Saturday, 15.04.2017 at 12:57.
 */
public interface UserDAO extends BaseDAO<User> {

    Collection<User> searchByName(String query);

    User findByUsername(String userName);

    boolean isRegistered(String userName, String password);

    User changePassword(User user);

    Collection<User> getAllSellers();

    Collection<User> getAllBuyers();

    void addBookmark(long carId, long userId);

    void deleteBookmark(long carId, long userId);

    Collection<Long> getBookmarkList(long id);
}
