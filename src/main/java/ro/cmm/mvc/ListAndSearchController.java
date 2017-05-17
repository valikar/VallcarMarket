package ro.cmm.mvc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ro.cmm.domain.Car;
import ro.cmm.domain.EngineType;
import ro.cmm.domain.SearchModel;
import ro.cmm.domain.TransmissionType;
import ro.cmm.service.CarService;
import ro.cmm.service.SecurityService;
import ro.cmm.service.ValidationException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Tamas on 4/24/2017.
 */
@Controller
@RequestMapping("/search")
public class ListAndSearchController {

    private static Logger LOGGER = LoggerFactory.getLogger("ListAndSearchController");

    @Autowired
    private CarService carService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView showSearch(SearchModel searchModel, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("search");

        if (searchModel.getManufacturer() == null) {
            searchModel.setManufacturer("All");
        }
        if (searchModel.getColour() == null) {
            searchModel.setColour("All");
        }
        if (searchModel.getType() == null) {
            searchModel.setType("All");
        }
        if (searchModel.getMatriculationStatus().size() == 0) {
            searchModel.getMatriculationStatus().add(true);
            searchModel.getMatriculationStatus().add(false);
        }
        if (searchModel.getTransmissionType().size() == 0) {
            searchModel.getTransmissionType().add(TransmissionType.MANUAL);
            searchModel.getTransmissionType().add(TransmissionType.AUTOMATIC);
        }
        if (searchModel.getEngineType().size() == 0) {
            searchModel.getEngineType().add(EngineType.DIESEL);
            searchModel.getEngineType().add(EngineType.PETROL);
        }

        List<String> errors = new LinkedList<>();
        boolean hasErrors = false;
        // because the fields: fabricationYear, price, mileAge are already initialized to 0
        // a binding error occurrence does not affect the integrity of the searchByUsername algorithm
        // basically we can ignore these errors, BUT ONLY FOR THESE 3 FIELDS
        // If other kind of binding error appears it will be handled correspondingly
        if(bindingResult.hasErrors()) {
            for (FieldError error: bindingResult.getFieldErrors()) {
                if (!(error.getField().equalsIgnoreCase("fabricationYear") ||
                        error.getField().equalsIgnoreCase("price") ||
                        error.getField().equalsIgnoreCase("mileage"))) {
                    errors.add(error.getField() + ":" + error.getCode());
                    hasErrors = true;
                }
            }
        }

        if(!hasErrors) {
            try {
                carService.validateSearchModel(searchModel);
                Collection<Car> results = carService.search(searchModel);
                modelAndView.addObject("results", results);

            } catch (ValidationException e) {
                errors = new LinkedList<>();
                errors.add(e.getMessage());
                modelAndView.addObject("results", new LinkedList<Car>());
                modelAndView.addObject("errors", errors);
                modelAndView.addObject("searchModel", searchModel);
            }
        } else {
            modelAndView.addObject("results", new LinkedList<Car>());
            modelAndView.addObject("errors", errors);
            modelAndView.addObject("searchModel", searchModel);
        }

        Map<String,List<String>> map = carService.getManufacturersAndTypes();

        modelAndView.addObject("map", map);
        modelAndView.addObject("colours", carService.getAllColors());
        modelAndView.addObject("user", securityService.getCurrentUser());

        return modelAndView;
    }

}
