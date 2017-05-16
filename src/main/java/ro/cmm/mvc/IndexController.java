package ro.cmm.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.cmm.domain.Car;
import ro.cmm.domain.User;
import ro.cmm.service.CarService;
import ro.cmm.service.UserService;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Joseph Saturday, 22.04.2017 at 03:46.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private CarService carService;

    @RequestMapping("")
    public ModelAndView index() {
        ModelAndView result = new ModelAndView("index");

        Collection<Car> carList = carService.listAll();
        int firstOne=carList.size()-2,secondOne=carList.size()-1,thirdOne=carList.size();
        Collection<Car> cars = new LinkedList<>();
        for (Car car : carList){
            firstOne--;secondOne--; thirdOne--;
            if (firstOne==0&&firstOne>=0){
                cars.add(car);
            }
            if (secondOne==0&&secondOne>=0){
                cars.add(car);
            }
            if (thirdOne==0&&thirdOne>=0){
                cars.add(car);
            }
        }
        result.addObject("cars", cars);

        return result;
    }

    @RequestMapping("denied")
    public ModelAndView denied(){
        ModelAndView modelAndView = new ModelAndView("denied");
        return modelAndView;
    }
}
