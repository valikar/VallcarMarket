package ro.cmm.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import ro.cmm.domain.LoginUser;
import ro.cmm.domain.Role;
import ro.cmm.domain.User;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Joseph Monday, 08.05.2017 at 19:55.
 */
public abstract class BaseLoginServiceTest {

    protected abstract LoginService getLoginService();
    protected abstract UserService getUserService();

    @After
    public void tearDown(){
        Collection<User> users = new LinkedList<>(getUserService().listAll());

        for (User user : users){
            getUserService().delete(user.getId());
        }
    }

    @Test
    public void test_verify_register() throws ValidationException {
        User u0 = new User();
        u0.setUserName("conormcgregor@yahoo.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);
        getUserService().save(u0);

        LoginUser l0 = new LoginUser();
        l0.setUserName("conormcgregor@yahoo.com");
        l0.setPassword("password");

        Assert.assertTrue(getLoginService().isRegistered(l0));
    }

    @Test
    public void test_verify_register_from_many() throws ValidationException {
        User u0 = new User();
        u0.setUserName("conormcgregor@yahoo.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);
        getUserService().save(u0);

        User u2 = new User();
        u2.setUserName("pop_iosifcristian@yahoo.com");
        u2.setPassword("password");
        u2.setPasswordValidation("password");
        u2.setFirstName("Pop");
        u2.setLastName("Iosif Cristian");
        u2.setRole(Role.BUYER);
        getUserService().save(u2);

        LoginUser l0 = new LoginUser();
        l0.setUserName("pop_iosifcristian@yahoo.com");
        l0.setPassword("password");

        Assert.assertTrue(getLoginService().isRegistered(l0));
    }

    @Test
    public void test_verify_not_registered() throws ValidationException {

        LoginUser l0 = new LoginUser();
        l0.setUserName("conormcgregor@yahoo.com");
        l0.setPassword("password");
        System.out.println(getUserService().listAll().toString());
        Assert.assertFalse(getLoginService().isRegistered(l0));
    }

    @Test(expected = ValidationException.class)
    public void test_empty_username() throws ValidationException {
        LoginUser l0 = new LoginUser();
        l0.setPassword("password");

        getLoginService().save(l0);
    }

    @Test(expected = ValidationException.class)
    public void test_empty_password() throws ValidationException {
        LoginUser l0 = new LoginUser();
        l0.setUserName("conormcgregor@yahoo.com");

        getLoginService().save(l0);
    }

    @Test(expected = ValidationException.class)
    public void test_empty() throws ValidationException {
        LoginUser l0 = new LoginUser();

        getLoginService().save(l0);
    }

    @Test(expected = ValidationException.class)
    public void test_invalid_credentials() throws ValidationException {
        User u0 = new User();
        u0.setUserName("conormcgregor@yahoo.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);
        getUserService().save(u0);

        LoginUser l0 = new LoginUser();
        l0.setUserName("conormcgregor@yahoo.com");
        l0.setPassword("drowssap");

        getLoginService().save(l0);
    }
}
