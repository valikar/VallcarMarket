package ro.cmm.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.cmm.domain.User;
import ro.cmm.service.UserService;

import java.util.Collection;

/**
 * Created by Joseph Saturday, 22.04.2017 at 03:46.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public ModelAndView index() {
        ModelAndView result = new ModelAndView("index");

        Collection<User> users = userService.listAll();
        result.addObject("users", users);

        return result;
    }
}
