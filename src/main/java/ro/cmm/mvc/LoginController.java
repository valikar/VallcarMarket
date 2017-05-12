package ro.cmm.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ro.cmm.domain.LoginUser;
import ro.cmm.service.LoginService;
import ro.cmm.service.UserService;
import ro.cmm.service.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Joseph Thursday, 20.04.2017 at 22:44.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    LoginService userLoginService;

    @RequestMapping("")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("user/login");

        return modelAndView;
    }

    @RequestMapping(value="/onLogin",method = RequestMethod.POST)
    public ModelAndView onLogin(@Valid @ModelAttribute("user") LoginUser user,
                                BindingResult bindingResult,
                                HttpServletRequest request) throws ValidationException {


        user.setUserName(request.getParameter("userName"));
        user.setPassword(request.getParameter("password"));

        ModelAndView modelAndView = new ModelAndView();

        boolean hasErrors = false;
        if (!bindingResult.hasErrors()){
            try {
                userLoginService.save(user);
                if (userLoginService.isRegistered(user)) {
                    request.getSession().setAttribute("currentUser", user);
                    user.setRole(userLoginService.getDao().getRole());
                    user.setId(userLoginService.getDao().getId());
                    user.setFullName(userLoginService.getDao().getFullName());
                    modelAndView.setView(new RedirectView("/"));
                }
                return modelAndView;

            } catch (ValidationException e) {
                for (String msg : e.getCauses()) {
                    bindingResult.addError(new ObjectError("userLogin", msg));
                }
                hasErrors = true;
            }
        } else {
            hasErrors = true;
        }

        if (hasErrors) {
            modelAndView = new ModelAndView("user/login");
            modelAndView.addObject("userLogin", user);
            modelAndView.addObject("errors", bindingResult.getAllErrors());
        }

        return modelAndView;
    }

    @RequestMapping("/logout")
    public String onLogout(HttpServletRequest request) {
        userService.getDao().logOut();
        request.getSession().invalidate();
        return "index";
    }

}