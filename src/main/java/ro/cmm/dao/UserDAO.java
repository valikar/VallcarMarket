package ro.cmm.dao;

import ro.cmm.domain.User;

import java.util.Collection;

/**
 * Created by Joseph Saturday, 15.04.2017 at 12:57.
 */
public interface UserDAO extends BaseDAO<User> {
    Collection<User> searchByName(String query);

}
