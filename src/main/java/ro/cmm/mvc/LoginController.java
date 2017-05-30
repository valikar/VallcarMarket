package ro.cmm.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Joseph Thursday, 20.04.2017 at 22:44.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(value = "" ,method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("user/login");

        return modelAndView;
    }

    @RequestMapping("/logout")
    public String onLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "index";
    }

}