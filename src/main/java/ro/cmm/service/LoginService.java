package ro.cmm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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
    private IMUserDAO dao;

    public boolean isRegistered(LoginUser loginUser){
    LOGGER.debug(loginUser.getUserName()+" tries to authenticate");
    boolean isRegistered = false;
    if (dao.isRegistered(loginUser.getUserName(),loginUser.getPassword())){
        isRegistered=true;
        LOGGER.debug(loginUser.getUserName()+" is registered");
    }
    if (!isRegistered){
        LOGGER.debug(loginUser.getUserName()+" is not registered");
    }
    return isRegistered;
    }

    public void save(LoginUser loginUser) throws ValidationException{
        LOGGER.debug("Validating user in login page");
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

        if(!dao.isRegistered(userName, password)) {
            errors.add("Invalid Credentials");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[] {}));
        }
    }

    public void setDao(IMUserDAO dao) {
        this.dao = dao;
    }

    public IMUserDAO getDao() {
        return dao;
    }
}
