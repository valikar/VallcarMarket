package ro.cmm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ro.cmm.domain.LoginUser;

/**
 * Created by Joseph Sunday, 23.04.2017 at 01:25.
 */
@Component
@Service
public class SecurityService {

    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);
    private ThreadLocal<LoginUser> currentUser;

    public void setCurrentUser(LoginUser loginUser) {
        this.currentUser = new ThreadLocal<>();
        this.currentUser.set(loginUser);
    }

    public LoginUser getCurrentUser() {
        return this.currentUser != null ?
                this.currentUser.get() : null;

    }


}