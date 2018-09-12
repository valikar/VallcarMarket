package ro.cmm.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


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