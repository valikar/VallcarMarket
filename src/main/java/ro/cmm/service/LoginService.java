package ro.cmm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ro.cmm.dao.UserDAO;
import ro.cmm.dao.inmemory.IMUserDAO;
import ro.cmm.domain.LoginUser;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Joseph Sunday, 23.04.2017 at 20:23.
 */
@Service
public class LoginService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private UserDAO dao;

    public boolean isRegistered(LoginUser loginUser){
    LOGGER.info(loginUser.getUserName()+" tries to authenticate");
    boolean isRegistered = false;
    if (dao.isRegistered(loginUser.getUserName(),loginUser.getPassword())){
        isRegistered=true;
        LOGGER.info(loginUser.getUserName()+" is registered");
    }
    if (!isRegistered){
        LOGGER.info(loginUser.getUserName()+" is not registered");
    }
    return isRegistered;
    }

    public void save(LoginUser loginUser) throws ValidationException{
        LOGGER.info("Validating user in login page");
        validate(loginUser.getUserName(),loginUser.getPassword());
    }

    private void validate(String userName, String password) throws ValidationException {
        List<String> errors = new LinkedList<String>();
        if (StringUtils.isEmpty(userName)) {
            errors.add("Empty Username");
        }

        if (StringUtils.isEmpty(password)) {
            errors.add("Empty Password");
        }

        if (dao.findByUsername(userName)!=null) {
            if (!dao.isRegistered(userName, password)) {
                errors.add("Invalid Credentials");
            }
        }else {
            errors.add("This account is not registered");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[] {}));
        }
    }

    public void setDao(UserDAO dao) {
        this.dao = dao;
    }

    public UserDAO getDao() {
        return dao;
    }
}
