package ro.cmm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ro.cmm.dao.CarDAO;
import ro.cmm.domain.Car;
import ro.cmm.domain.EngineType;
import ro.cmm.domain.TransmissionType;

import java.util.*;

/**
 * Created by Tamas on 4/22/2017.
 */
@Service
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

    public Collection<Car> bigSearch(String manufacturer, String type, int fabricationYear) {
        Collection<Car> allCars = dao.getAll();
        allCars = filterByManufacturer(manufacturer, allCars);
        allCars = filterByType(type, allCars);
        allCars = filterByFabricationYear(fabricationYear,allCars);

        return allCars;
    }

    private Collection<Car> filterByManufacturer(String manufacturer, Collection<Car> cars) {
        if (manufacturer.equalsIgnoreCase("all")) {
            return cars;
        }
        Iterator<Car> it = cars.iterator();
        while (it.hasNext()) {
            Car currentCar = it.next();
            if(!currentCar.getManufacturer().equalsIgnoreCase(manufacturer)) {
                it.remove();
            }
        }
        return cars;
    }

    private Collection<Car> filterByType(String type, Collection<Car> cars) {
        if (type.equalsIgnoreCase("all")) {
            return cars;
        }
        Iterator<Car> it = cars.iterator();
        while (it.hasNext()) {
            Car currentCar = it.next();
            if(!currentCar.getType().equalsIgnoreCase(type)) {
                it.remove();
            }
        }
        return cars;
    }

    private Collection<Car> filterByFabricationYear(int newerThanThisYear, Collection<Car> cars) {
        // valoare default sa fie 0, caz in care vrem sa vedem
        // toate masinile indiferent de varsta
        if (newerThanThisYear == 0) {
            return cars;
        }
        Iterator<Car> it = cars.iterator();
        while (it.hasNext()) {
            Car currentCar = it.next();
            if(currentCar.getFabricationYear() < newerThanThisYear) {
                it.remove();
            }
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

        // Validare pentru color vom face mai tarziu cand ne hotaram
        // cum sa fie input field-ul pentru culoare

        if(car.getExtras() == null) {
            errors.add("Description is missing");
        }

        // validare foarte basic, mai tarziu putem modifica sa fie mai smecher
        // valoarea default pt CarLocation poate sa fie (0,0)

        /*
        long carsLatitude = car.getLocation().getLatitude();
        long carsLongitude = car.getLocation().getLongtitude();
        if (carsLatitude < 0 || carsLatitude > 90) {
            errors.add("Invalid latitude value for the cars coordinates");
        }
        if (carsLongitude < 0 || carsLongitude > 180) {
            errors.add("Invalid longitude value for the cars coordinates");
        }
        */

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

    public Car getById(long id) {
        return dao.findById(id);
    }
}
