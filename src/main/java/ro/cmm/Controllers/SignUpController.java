package ro.cmm.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ro.cmm.Models.User;
import ro.cmm.service.SecurityService;
import ro.cmm.service.UserService;
import ro.cmm.service.ValidationException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;


@Controller
@RequestMapping(value = "/signup")
public class SignUpController {

    private static Logger LOGGER = LoggerFactory.getLogger("SignUpController");
    @Autowired
    private JavaMailSender sender;
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "")
    public ModelAndView signup(){
        ModelAndView modelAndView = new ModelAndView("user/signup");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @RequestMapping("/save")
    public ModelAndView save(
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        boolean hasErrors = false;

        if (!userService.verifyUsername(user.getUserName())){
            hasErrors=true;
            bindingResult.addError(new ObjectError(user.getUserName()," please pick another username"));
        }

        if (!bindingResult.hasErrors()) {
            try {
                userService.save(user);
                sendMail(user.getUserName());
                securityService.autoLogin(user.getUserName(), user.getPassword());
                modelAndView.addObject("currentUser",
                        securityService.getCurrentUser());
                RedirectView redirectView = new RedirectView("/");
                modelAndView.setView(redirectView);
            }catch (ValidationException e) {
                for (String msg : e.getCauses()) {
                    bindingResult.addError(new ObjectError("userLogin", msg));
                }
                hasErrors = true;
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } else {
            hasErrors = true;
        }
        if (hasErrors){
            modelAndView = new ModelAndView("user/signup");
            modelAndView.addObject("errors", bindingResult.getAllErrors());
            modelAndView.addObject("user", user);
        }
        return modelAndView;
    }
    private void sendMail(String email) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setText("<h1>Hello!</h1> this is <a href='http://localhost:8080'>VallcarNarket</a>", true);
        helper.setSubject("Mail from VallcarMarket");
        helper.setTo(email);

        sender.send(message);
    }
}
