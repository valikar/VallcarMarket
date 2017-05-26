package ro.cmm.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.cmm.domain.Car;
import ro.cmm.service.CarService;
import ro.cmm.service.SecurityService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Created by Joseph Saturday, 22.04.2017 at 03:46.
 */
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

//        Collection<Car> carList = carService.listAllAvailableCars();
//        int firstOne=carList.size()-2,secondOne=carList.size()-1,thirdOne=carList.size();
//        Collection<Car> cars = new LinkedList<>();
//        for (Car car : carList){
//            firstOne--;secondOne--; thirdOne--;
//            if (firstOne==0&&firstOne>=0){
//                cars.add(car);
//            }
//            if (secondOne==0&&secondOne>=0){
//                cars.add(car);
//            }
//            if (thirdOne==0&&thirdOne>=0){
//                cars.add(car);
//            }
//        }
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
