package ro.cmm.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans.xml"})
public class UserServiceTest extends BaseUserServiceTest {

    @Autowired
    private UserService userService;

    @Override
    protected UserService getUserService(){
        return userService;
    }
}
