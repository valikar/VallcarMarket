package ro.cmm.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import ro.cmm.dao.inmemory.IMUserDAO;
import ro.cmm.domain.User;

import java.util.*;

/**
 * Created by Joseph Saturday, 15.04.2017 at 01:57.
 */

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

    public boolean delete(User user) {
        LOGGER.debug("Deleting user with id: " + user.getId());
        User u = dao.findById(user.getId());
        if (u != null) {
            dao.delete(u);
            return true;
        }
        return false;
    }


    public Collection<User> search( String query) {
        LOGGER.debug("Searching for " + query);
        return dao.searchByName(query);
    }

    public Collection<User> listAll() {
        LOGGER.debug("Listing users ");

        return dao.getAll();
    }

    public Collection<User> listAllSellers() {
        LOGGER.debug("Listing sellers ");
        return dao.getAllSellers();
    }

    public Collection<User> listAllBuyers() {
        LOGGER.debug("Listing buyers ");
        return dao.getAllBuyers();
    }

    private void validate(User user) throws ValidationException {

        List<String> errors = new LinkedList<String>();

        if (StringUtils.isEmpty(user.getUserName())) {
            errors.add("User Name is Empty");
        }

        if (StringUtils.isEmpty(user.getPassword())) {
            errors.add("Password is Empty");
        }

        if (StringUtils.isEmpty(user.getLastName())) {
            errors.add("Last Name is Empty");
        }

        if (StringUtils.isEmpty(user.getLastName())) {
            errors.add("Last Name is Empty");
        }

        if (StringUtils.isEmpty(user.getRole())) {
            errors.add("Role is Empty");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[]{}));
        }
    }
}
