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
import ro.cmm.domain.User;
import ro.cmm.service.LoginService;
import ro.cmm.service.SecurityService;
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

    @Autowired
    SecurityService securityService;

    @RequestMapping("")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("user/login");

        return modelAndView;
    }

    @RequestMapping(value="/onLogin",method = RequestMethod.POST)
    public ModelAndView onLogin(@Valid @ModelAttribute("loginUser") LoginUser loginUser,
                                BindingResult bindingResult,
                                HttpServletRequest request) throws ValidationException {


        loginUser.setUserName(request.getParameter("userName"));
        loginUser.setPassword(request.getParameter("password"));

        ModelAndView modelAndView = new ModelAndView();

        boolean hasErrors = false;
        if (!bindingResult.hasErrors()){
            try {
                userLoginService.save(loginUser);
                if (userLoginService.isRegistered(loginUser)) {
                    User user = userLoginService.getDao().findByUsername(loginUser.getUserName());
                    loginUser.setId(user.getId());
                    loginUser.setFullName(user.getFirstName()+" "+user.getLastName());
                    loginUser.setRole(user.getRole());
                    securityService.setCurrentUser(loginUser);
                    request.getSession().setAttribute("currentUser", loginUser);

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
            modelAndView.addObject("userLogin", loginUser);
            modelAndView.addObject("errors", bindingResult.getAllErrors());
        }

        return modelAndView;
    }

    @RequestMapping("/logout")
    public String onLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "index";
    }

}