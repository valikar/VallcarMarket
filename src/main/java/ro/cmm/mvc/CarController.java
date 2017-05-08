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
import ro.cmm.service.CarService;
import ro.cmm.service.LoginService;
import ro.cmm.service.ValidationException;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

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

    @RequestMapping("/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("car/add");
        modelAndView.addObject("car", new Car());
        return modelAndView;
    }

    @RequestMapping("/save")
    public ModelAndView save(@Valid  Car car,
                             BindingResult bindingResult,
                             MultipartFile file) {
                            //BindingResult fileBindingResult

        ModelAndView modelAndView = new ModelAndView();
        boolean hasErrors = false;

        if (!bindingResult.hasErrors()) {
                try {
                    car.setSellerId(userLoginService.getDao().getId());

                    //saving the file and setting the cars imgUrl field
                    File localFile = new File(localFilesDir, System.currentTimeMillis() +"_" + file.getOriginalFilename());
                    file.transferTo(localFile);
                    car.setImgUrl(localFile.getName());

                    carService.save(car);

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
