package ro.cmm.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans.xml"})
public class MessageServiceTest extends BaseMessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Override
    protected MessageService getMessageService() {
        return messageService;
    }

    @Override
    protected UserService getUserService() {
        return userService;
    }

    @Override
    protected CarService getCarService() {
        return carService;
    }
}
