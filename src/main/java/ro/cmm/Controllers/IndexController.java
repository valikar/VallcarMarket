package ro.cmm.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.cmm.Models.Car;
import ro.cmm.service.CarService;
import ro.cmm.service.SecurityService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private CarService carService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping("")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView result = new ModelAndView("index");

        Collection<Car> cars = carService.getDao().getLatestCars();

        result.addObject("cars", cars);
        request.getSession().setAttribute("currentUser", securityService.getCurrentUser());
        return result;
    }

    @RequestMapping("access-denied")
    public ModelAndView denied(){
        ModelAndView modelAndView = new ModelAndView("denied");
        return modelAndView;
    }
}
