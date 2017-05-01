package ro.cmm.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ro.cmm.domain.Car;
import ro.cmm.domain.User;
import ro.cmm.service.CarService;
import ro.cmm.service.LoginService;
import ro.cmm.service.UserService;
import ro.cmm.service.ValidationException;

import javax.validation.Valid;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Joseph Monday, 24.04.2017 at 20:51.
 */
@Controller
@RequestMapping("/account")
public class AccountController {


    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Autowired
    private LoginService loginService;

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

    @RequestMapping("/edit")
    public ModelAndView edit(long id) {
        User user = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView("/user/edit");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping("/save")
    public ModelAndView save(
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        boolean hasErrors = false;

        if (!bindingResult.hasErrors()) {
            try {
                userService.save(user);
                RedirectView redirectView = new RedirectView("/");
                modelAndView.setView(redirectView);
            }catch (ValidationException e) {
                for (String msg : e.getCauses()) {
                    bindingResult.addError(new ObjectError("userLogin", msg));
                }
                hasErrors = true;
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

    @RequestMapping("/list/car")
    public ModelAndView display(long id) {
        ModelAndView modelAndView = new ModelAndView("/car/display");
        if (carService.getById(id)!=null) {
            Car car = carService.getById(id);

            modelAndView.addObject("car", car);
        }
        return modelAndView;
    }

    @RequestMapping("/list")
    public ModelAndView list(long id){
        ModelAndView modelAndView = new ModelAndView("/car/list");
        if (carService.getCarListOfSeller(id)!=null){
            Collection<Car> cars = carService.getCarListOfSeller(id);
            modelAndView.addObject("cars",cars);
        }else {
            modelAndView.addObject(new RedirectView("/"));
        }
        return modelAndView;
    }

    @RequestMapping("/bookmark/list")
    public ModelAndView bookmarks(long id){
        ModelAndView modelAndView = new ModelAndView("/car/bookmarks");
        if (userService.getBookmarks(id)!=null){
            Collection<Long> bookmarks = userService.getBookmarks(id);
            Collection<Car> cars = new LinkedList<>();
            for (Long l : bookmarks){
                cars.add(carService.getById(l));
            }
            modelAndView.addObject("cars",cars);
        }else {
            Collection<Car> cars = new LinkedList<>();
            modelAndView.addObject("cars",cars);
            modelAndView.addObject(new RedirectView("/"));
        }
        return modelAndView;
    }

    @RequestMapping("/bookmark/delete")
    public String deleteBookmark(long id){
        userService.deleteBookmark(id);
        return "redirect:/account/bookmark/list?id="+Long.toString(loginService.getImUserDAO().getId());
    }

    @RequestMapping("/bookmark")
    public String bookmark(long id){
    userService.addBookmark(id);
    return "redirect:/account/list/car?id="+Long.toString(id);
    }
}
