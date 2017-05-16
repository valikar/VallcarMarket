package ro.cmm.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ro.cmm.domain.Car;
import ro.cmm.domain.CarLocation;
import ro.cmm.service.CarService;
import ro.cmm.service.LoginService;
import ro.cmm.service.SecurityService;
import ro.cmm.service.ValidationException;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Emanuel Pruker
 */
@Controller
@RequestMapping("/car")
public class CarController {

    @Value("${local.files.dir}")
    private String localFilesDir;

    @Autowired
    private CarService carService;

    @Autowired
    private LoginService userLoginService;

    @Autowired
    private SecurityService securityService;

    private final Map<Long, String> carToLastImgURL = new HashMap<>();

    @RequestMapping("/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("car/add");
        Map<String,List<String>> map = carService.getManufacturersAndTypes();

//        if (carModel.getManufacturer() == null) {
//            carModel.setManufacturer("All");
//        }
//        if (carModel.getColour() == null) {
//            carModel.setColour("All");
//        }
//        if (carModel.getType() == null) {
//            carModel.setType("All");
////        }
//
//        String searchModelManufacturer = carModel.getManufacturer();
//        String searchModelType = carModel.getType();
//
//        List<String> typeListForManufacturer = map.get(searchModelManufacturer);
//
//        if(!typeListForManufacturer.contains(searchModelType)) {
//            carModel.setType("All");
//        }
        modelAndView.addObject("car", new Car());
        modelAndView.addObject("map", map);
        modelAndView.addObject("colours", carService.getAllColors());
        return modelAndView;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(long id) {
        Car car = carService.getById(id);
        CarLocation carLocation = car.getLocation();
        Map<String,List<String>> map = carService.getManufacturersAndTypes();
        ModelAndView modelAndView = new ModelAndView("car/add");
        modelAndView.addObject("car", car);
        modelAndView.addObject("carLocation",carLocation);
        modelAndView.addObject("map", map);
        modelAndView.addObject("colours", carService.getAllColors());
        return modelAndView;
    }

    @RequestMapping("/save")
    public ModelAndView save(@Valid  Car car,
                             CarLocation carLocation,
                             BindingResult bindingResult,
                             MultipartFile file) {
                            //BindingResult fileBindingResult

        ModelAndView modelAndView = new ModelAndView();
        boolean hasErrors = false;

        if (!bindingResult.hasErrors()) {
                try {
                    car.setSellerId(securityService.getCurrentUser().getId());

                    //saving the file and setting the cars imgUrl field
                    long id = car.getId();
                    String imgUrl = null;
                    if(file != null && !file.getOriginalFilename().isEmpty()) {
                        File localFile = new File(localFilesDir, System.currentTimeMillis() +"_" + file.getOriginalFilename());
                        file.transferTo(localFile);
                        imgUrl = localFile.getName();
                        car.setImgUrl(imgUrl);

                    } else if (carToLastImgURL.containsKey(id)){

                        car.setImgUrl(carToLastImgURL.get(id));
                    }
                    car.setLocation(carLocation);
                    car = carService.save(car);
                    if (id == 0) {
                        carToLastImgURL.put(car.getId(),imgUrl);
                    }

                    RedirectView redirectView = new RedirectView("/");
                    modelAndView.setView(redirectView);
                } catch (ValidationException ex) {
                    for (String msg : ex.getCauses()) {
                        bindingResult.addError(new ObjectError("userLogin", msg));
                    }
                    hasErrors = true;
                } catch (IOException e) {
                    bindingResult.addError(new ObjectError("fileUpload", e.getMessage()));
                }
        } else {
            hasErrors = true;
        }

        if (hasErrors){
            modelAndView = new ModelAndView("car/add");
            modelAndView.addObject("errors", bindingResult.getAllErrors());
            modelAndView.addObject("car", car);
        }

        System.out.println(car);


        return modelAndView;
    }
}
