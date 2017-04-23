package ro.cmm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ro.cmm.domain.Car;
import ro.cmm.service.CarService;
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
            if (!bindingResult.hasErrors()) {
                try {
                    carService.save(car);
                    RedirectView redirectView = new RedirectView("/car");
                    modelAndView.setView(redirectView);
                }catch (ValidationException ex) {

                    //LOGGER.error("validation error", ex);

                    List<String> errors = new LinkedList<>();
                    errors.add(ex.getMessage());
                    modelAndView = new ModelAndView("car/add");
                    modelAndView.addObject("errors", errors);
                    modelAndView.addObject("car", car);
                }

            } else {
                List<String> errors = new LinkedList<>();

                for (FieldError error:
                        bindingResult.getFieldErrors()) {
                    errors.add(error.getField() + ":" + error.getCode());
                }

                modelAndView = new ModelAndView("car/add");
                modelAndView.addObject("errors", errors);
                modelAndView.addObject("car", car);
            }


            return modelAndView;
    }
}
