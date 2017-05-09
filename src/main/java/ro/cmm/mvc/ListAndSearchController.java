package ro.cmm.mvc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.cmm.domain.Car;
import ro.cmm.domain.EngineType;
import ro.cmm.domain.SearchModel;
import ro.cmm.domain.TransmissionType;
import ro.cmm.service.CarService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Tamas on 4/24/2017.
 */
@Controller
@RequestMapping("")
public class ListAndSearchController {

    private static Logger LOGGER = LoggerFactory.getLogger("ListAndSearchController");

    @Autowired
    private CarService carService;

    @RequestMapping("/search")
    public ModelAndView showSearch(SearchModel searchModel, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("search");
        Map<String,List<String>> map = carService.getManufacturersAndTypes();

        if (searchModel.getManufacturer() == null) {
            searchModel.setManufacturer("All");
        }
        if (searchModel.getColour() == null) {
            searchModel.setColour("All");
        }
        if (searchModel.getType() == null) {
            searchModel.setType("All");
        }
        if (searchModel.getTransmissionType().size() == 0) {
            searchModel.getTransmissionType().add(TransmissionType.MANUAL);
            searchModel.getTransmissionType().add(TransmissionType.AUTOMATIC);
        }
        if (searchModel.getEngineType().size() == 0) {
            searchModel.getEngineType().add(EngineType.DIESEL);
            searchModel.getEngineType().add(EngineType.PETROL);
        }

        // if the user selects another manufacturer the form will submitForm automatically
        // Lets say we have a scenario where the manufacturer and type options look like this:
        //               Manufacturer: Ferrari        Type: LaFerrari
        // Now if the user wants to choose Audi for the manufacturer
        // the form will submitForm with the following values(because of autoSubmit)
        //               Manufacturer: Audi        Type: LaFerrari
        // to avoid this we must check if the types name is compatible with manufacturers name
        // if not, the type will be set to "All" and all cars will be printed from the manufacturer
        String searchModelManufacturer = searchModel.getManufacturer();
        String searchModelType = searchModel.getType();

        List<String> typeListForManufacturer = map.get(searchModelManufacturer);

        if(!typeListForManufacturer.contains(searchModelType)) {
            searchModel.setType("All");
        }

        Collection<Car> results = carService.search(searchModel);

        modelAndView.addObject("map", map);
        modelAndView.addObject("colours", carService.getAllColors());
        modelAndView.addObject("results", results);
        modelAndView.addObject("searchModel", searchModel);

        return modelAndView;
    }

}
