package ro.cmm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import ro.cmm.dao.CarDAO;
import ro.cmm.domain.Car;
import ro.cmm.domain.EngineType;
import ro.cmm.domain.TransmissionType;

import java.util.*;

/**
 * Created by Tamas on 4/22/2017.
 */
public class CarService {

    @Autowired
    private CarDAO dao;

    public Collection<Car> listAll() {
        return dao.getAll();
    }

//    public Collection<Car> search(String query) {
//        return dao.searchByName(query);
//    }

    public boolean delete(Long id) {
        Car car = dao.findById(id);
        if (car != null) {
            dao.delete(car);
            return true;
        }

        return false;
    }

    public Collection<Car> search(String manufacturer, String type, int fromYear,
                                  int mileAge, int price, EngineType[] engineTypes,
                                  TransmissionType[] transmissionTypes, String colour) {

        Collection<Car> allCars = dao.getAll();

        if (manufacturer != null) {
            allCars = filterByManufacturer(manufacturer, allCars);
        }
        if (type != null) {
            allCars = filterByType(type, allCars);
        }
        if (fromYear != 0) {
            allCars = filterByFabricationYear(fromYear, allCars);
        }
        if (mileAge != 0) {
            allCars = filterByMileAge(mileAge, allCars);
        }
        if (price != 0) {
            allCars = filterByPrice(price, allCars);
        }
        if (engineTypes.length != EngineType.values().length) {
            allCars = filterByEngineType(engineTypes, allCars);
        }
        if (transmissionTypes.length != TransmissionType.values().length) {
            allCars = filterByTransmissionType(transmissionTypes, allCars);
        }
        if (colour != null) {
            allCars = filterByPrice(price, allCars);
        }

        return allCars;
    }

    private Collection<Car> filterByManufacturer(String manufacturer, Collection<Car> cars) {

        if(!manufacturer.equalsIgnoreCase("all")) {
            cars.removeIf((Car car) -> !car.getManufacturer().equalsIgnoreCase(manufacturer));
        }
        return cars;
    }

    private Collection<Car> filterByType(String type, Collection<Car> cars) {
        if(!type.equalsIgnoreCase("all")) {
            cars.removeIf((Car car) -> !car.getType().equalsIgnoreCase(type));
        }
        return cars;
    }

    private Collection<Car> filterByFabricationYear(int fromYear, Collection<Car> cars) {
        cars.removeIf((Car car) -> car.getFabricationYear() < fromYear);

        return cars;
    }

    private Collection<Car> filterByMileAge(int maxMileAge, Collection<Car> cars) {
        cars.removeIf((Car car) -> car.getMileAge() > maxMileAge);

        return cars;
    }

    private Collection<Car> filterByPrice(int price, Collection<Car> cars) {
        cars.removeIf((Car car) -> car.getPrice() > price);

        return cars;
    }

    private Collection<Car> filterByEngineType(EngineType[] engineTypes, Collection<Car> cars) {
        List<EngineType> engineTypeList = Arrays.asList(engineTypes);

        cars.removeIf((Car car) -> !engineTypeList.contains(car.getEngineType()));

        return cars;
    }

    private Collection<Car> filterByTransmissionType(TransmissionType[] transmissionTypes, Collection<Car> cars) {
        List<TransmissionType> transmissionTypeList = Arrays.asList(transmissionTypes);

        cars.removeIf((Car car) -> !transmissionTypeList.contains(car.getTransmissionType()));

        return cars;
    }

    private Collection<Car> filterByColour(String colour, Collection<Car> cars) {
        if(!colour.equalsIgnoreCase("all")) {
            cars.removeIf((Car car) -> !car.getColour().equalsIgnoreCase(colour));
        }
        return cars;
    }


    public void save(Car car) throws ValidationException {
        validate(car);
        dao.update(car);
    }

    // nu include validare pentru imgURL !!!!!!!!!!!!!!!!!!!!!!!!!
    private void validate(Car car) throws ValidationException {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        List<String> errors = new LinkedList<String>();
        if (StringUtils.isEmpty(car.getManufacturer())) {
            errors.add("Car Manufacturer is Empty");
        }

        if (StringUtils.isEmpty(car.getType())) {
            errors.add("Car Type is Empty");
        }

        // Daca o sa folosim @NotNull la fielduri cred ca vom
        // putea scoate prima verificare de aici
        if(car.getFabricationYear() == 0) {
            errors.add("Fabrication Year is Empty");
        } else {
            if(car.getFabricationYear() > currentYear) {
                errors.add("Car fabrication date in future");
            }

            if (car.getFabricationYear() < 1950) {
                errors.add("Car is too old");
            }
        }

        if (car.getMileAge() < 0) {
            errors.add("The cars mileage cannot be a negative value.");
        }

        if (car.getPrice() < 0) {
            errors.add("The cars price cannot be a negative value.");
        }

        if (!Arrays.asList(EngineType.values()).contains(car.getEngineType())) {
            errors.add("Invalid engine type");
        }

        if (!Arrays.asList(TransmissionType.values()).contains(car.getTransmissionType())) {
            errors.add("Invalid transmission type");
        }

        if (car.getColour() == null) {
            errors.add("The colour cannot be empty");
        }

        if(car.getExtras() == null) {
            errors.add("Description is missing");
        }

        // validare foarte basic, mai tarziu putem modifica sa fie mai smecher
        // valoarea default pt CarLocation poate sa fie (0,0)
        if (car.getLocation() == null) {
            errors.add("Car location does not exist");
        } else {
            long carsLatitude = car.getLocation().getLatitude();
            long carsLongitude = car.getLocation().getLongtitude();
            if (carsLatitude < 0 || carsLatitude > 90) {
                errors.add("Invalid latitude value for the cars coordinates");
            }
            if (carsLongitude < 0 || carsLongitude > 180) {
                errors.add("Invalid longitude value for the cars coordinates");
            }
        }

        if(car.getImgUrl() == null) {
            errors.add("Img url is empty.");
        }

        // la isAvailable nu prea avem ce valida

        // la isMatriculated la fel

        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[] {}));
        }
    }

    public CarDAO getDao() {
        return dao;
    }

    public void setDao(CarDAO dao) {
        this.dao = dao;
    }
}
