package ro.cmm.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Joseph Monday, 24.04.2017 at 20:51.
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @RequestMapping("/seller")
    public ModelAndView seller() {
        ModelAndView modelAndView = new ModelAndView("/user/seller");
        return modelAndView;
    }

    @RequestMapping("/buyer")
    public ModelAndView buyer() {
        ModelAndView modelAndView = new ModelAndView("/user/buyer");
        return modelAndView;
    }
}
