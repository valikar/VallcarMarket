package ro.cmm.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ro.cmm.domain.Car;
import ro.cmm.service.CarService;
import ro.cmm.service.LoginService;
import ro.cmm.service.UserService;
import ro.cmm.service.ValidationException;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Emanuel Pruker
 */
@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private LoginService userLoginService;

    @RequestMapping("/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("car/add");
        modelAndView.addObject("car", new Car());
        return modelAndView;
    }

    @RequestMapping("/save")
    public ModelAndView save(@Valid  Car car,
                             BindingResult bindingResult) {

            ModelAndView modelAndView = new ModelAndView();
        boolean hasErrors = false;

        if (!bindingResult.hasErrors()) {
                try {
                    car.setSellerId(userLoginService.getImUserDAO().getId());
                    carService.save(car);
                    RedirectView redirectView = new RedirectView("/");
                    modelAndView.setView(redirectView);
                }catch (ValidationException ex) {
                    for (String msg : ex.getCauses()) {
                        bindingResult.addError(new ObjectError("userLogin", msg));
                    }
                    hasErrors = true;
                }
            } else {
            hasErrors = true;
        }
        if (hasErrors){
            modelAndView = new ModelAndView("car/add");
            modelAndView.addObject("errors", bindingResult.getAllErrors());
            modelAndView.addObject("car", car);
        }

            return modelAndView;
    }
}
