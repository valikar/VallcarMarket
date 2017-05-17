package ro.cmm.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ro.cmm.dao.CarDAO;
import ro.cmm.domain.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Tamas on 4/22/2017.
 */
@Service
public class CarService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    @Autowired
    private CarDAO dao;

    public Collection<Car> listAll() {
        LOGGER.debug("Returning all cars from the database.");
        return dao.getAll();
    }

    public Collection<Car> listAllAvailableCars(){
        LOGGER.debug("Returning all available cars from the database.");
        Collection<Car> cars = new LinkedList<>();
        for (Car car: dao.getAll()){
            if (car.getAvailable())
                cars.add(car);
        }
        return cars;
    }

    public boolean delete(Long id) {
        LOGGER.debug("Deleteing car with id: " + id);
        Car car = dao.findById(id);
        if (car != null) {
            dao.delete(car);
            return true;
        }

        return false;
    }
    public Collection<Car> search(SearchModel searchModel) {
        LOGGER.debug("Searching for cars with the following parameters: " + searchModel.toString());
        String manufacturer = searchModel.getManufacturer();
        String type = searchModel.getType();
        int fromYear = searchModel.getFabricationYear();
        int mileAge = searchModel.getMileAge();
        int price = searchModel.getPrice();
        List<EngineType> engineTypes = searchModel.getEngineType();
        List<TransmissionType> transmissionTypes = searchModel.getTransmissionType();
        String color = searchModel.getColour();
        Collection<Car> cars = search(manufacturer, type, fromYear,
                                    mileAge, price, engineTypes, transmissionTypes, color);
        return cars;

    }

    public Collection<Car> search(String manufacturer, String type, int fromYear,
                                  int mileAge, int price, List<EngineType> engineTypes,
                                  List<TransmissionType> transmissionTypes, String colour) {

        Collection<Car> allCars = new LinkedList<>(dao.getAll());

        allCars = filterByAvailability(allCars);

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
        if (EngineType.values().length != engineTypes.size()) {
            allCars = filterByEngineType(engineTypes, allCars);
        }
        if (TransmissionType.values().length != transmissionTypes.size()) {
            allCars = filterByTransmissionType(transmissionTypes, allCars);
        }
        if (colour != null) {
            allCars = filterByColour(colour, allCars);
        }

        return allCars;
    }

    private Collection<Car> filterByAvailability(Collection<Car> cars) {
        LOGGER.debug("Filtering cars by availability.");
        cars.removeIf((Car car) -> !car.getAvailable());

        return cars;
    }

    private Collection<Car> filterByManufacturer(String manufacturer, Collection<Car> cars) {
        LOGGER.debug("Filtering cars manufacturers.");

        if(!manufacturer.equalsIgnoreCase("All")) {
            cars.removeIf((Car car) -> !car.getManufacturer().equalsIgnoreCase(manufacturer));
        }
        return cars;
    }

    private Collection<Car> filterByType(String type, Collection<Car> cars) {
        LOGGER.debug("Filtering cars by type.");
        if(!type.equalsIgnoreCase("All")) {
            cars.removeIf((Car car) -> !car.getType().equalsIgnoreCase(type));
        }
        return cars;
    }

    private Collection<Car> filterByFabricationYear(int fromYear, Collection<Car> cars) {
        LOGGER.debug("Filtering cars by the year of fabrication.");
        cars.removeIf((Car car) -> car.getFabricationYear() < fromYear);

        return cars;
    }

    private Collection<Car> filterByMileAge(int maxMileAge, Collection<Car> cars) {
        LOGGER.debug("Filtering cars by their mileage.");
        cars.removeIf((Car car) -> car.getMileAge() > maxMileAge);

        return cars;
    }

    private Collection<Car> filterByPrice(int price, Collection<Car> cars) {
        LOGGER.debug("Filtering cars by price.");
        cars.removeIf((Car car) -> car.getPrice() > price);

        return cars;
    }

    private Collection<Car> filterByEngineType(List<EngineType> engineTypes, Collection<Car> cars) {
        LOGGER.debug("Filtering cars by engine type.");
        cars.removeIf((Car car) -> !engineTypes.contains(car.getEngineType()));

        return cars;
    }

    private Collection<Car> filterByTransmissionType(List<TransmissionType> transmissionTypes, Collection<Car> cars) {
        LOGGER.debug("Filtering cars by transmission type.");
        cars.removeIf((Car car) -> !transmissionTypes.contains(car.getTransmissionType()));

        return cars;
    }

    private Collection<Car> filterByColour(String colour, Collection<Car> cars) {
        LOGGER.debug("Filtering cars by color.");
        if(!colour.equalsIgnoreCase("all")) {
            cars.removeIf((Car car) -> !car.getColour().equalsIgnoreCase(colour));
        }
        return cars;
    }

    public Car save(Car car) throws ValidationException {
        validate(car);
        LOGGER.debug("Saving car with the following parameters: " + car.toString());
        return dao.update(car);

    }

    public void validateSearchModel(SearchModel searchModel) throws ValidationException {
        LOGGER.debug("Validating car with the following parameters: " + searchModel.toString());

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        List<String> errors = new LinkedList<String>();

        if (StringUtils.isEmpty(searchModel.getManufacturer())) {
            errors.add("Car Manufacturer is Empty");
        }

        if (StringUtils.isEmpty(searchModel.getType())) {
            errors.add("Car Type is Empty");
        }

        if(searchModel.getFabricationYear() > currentYear) {
            errors.add("Car fabrication date in future");
        }
        if (searchModel.getFabricationYear() < 1950 && searchModel.getFabricationYear() != 0) {
            errors.add("Car is too old");
        }

        if (searchModel.getMileAge() < 0) {
            errors.add("The cars mileage cannot be a negative value.");
        }

        if (searchModel.getPrice() < 0) {
            errors.add("The cars price cannot be a negative value.");
        }

        if (searchModel.getTransmissionType().isEmpty()) {
            errors.add("No transmission type selected");
        }

        if (searchModel.getEngineType().isEmpty()) {
            errors.add("No engine type selected");
        }

        if (!Arrays.asList(EngineType.values()).containsAll(searchModel.getEngineType())) {
            errors.add("Invalid engine type");
        }

        if (!Arrays.asList(TransmissionType.values()).containsAll(searchModel.getTransmissionType())) {
            errors.add("Invalid transmission type");
        }

        if(searchModel.getColour() == null) {
            errors.add("No color selected!");
        }

        if (searchModel.getMatriculationStatus().isEmpty()) {
            errors.add("No matriculation status selected!");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[] {}));
        }
    }

    private void validate(Car car) throws ValidationException {
        LOGGER.debug("Validating car with the following parameters: " + car.toString());
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        List<String> errors = new LinkedList<String>();

        if (StringUtils.isEmpty(car.getManufacturer())) {
            errors.add("Car Manufacturer is Empty");
        }

        if (StringUtils.isEmpty(car.getType())) {
            errors.add("Car Type is Empty");
        }

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

        if(car.getColour() == null) {
            errors.add("No color selected!");
        }

        if(car.getExtras() == null) {
            errors.add("Description is missing!");
        }

//        if(car.getImgUrl() == null) {
//            errors.add("Img url is empty.");
//        }

        if (car.getAvailable() == null) {
            errors.add("Car availability is empty!");
        }

        if (car.getMatriculated() == null) {
            errors.add("Car matriculation status is empty!");
        }

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

    // am folosit-o in ListAndSearchController pentru a incarca
    // marcile si tipurile disponibile
    // dupa ce trecem la DB real vom putea sterge/modifica metoda asta
    public Map<String, List<String>> getManufacturersAndTypes() {
//        Map<String,List<String>> cars = new TreeMap<>();
//        List<String> audis = new LinkedList<>();
//        audis.add("A4");
//        audis.add("A5");
//        List<String> vw = new LinkedList<>();
//        vw.add("Golf");
//        vw.add("Polo");
//
//        List<String> ferraris = new LinkedList<>();
//        ferraris.add("Laferrari");
//        ferraris.add("458 Italia");
//
//        List<String> all = new LinkedList<>();
//        all.add("All");
//
//        cars.put("Audi",audis);
//        cars.put("Vw", vw);
//        cars.put("Ferrari", ferraris);
//        cars.put("All", all);

        LOGGER.debug("Retrieving the list of the car manufacturers and types from the database.");
        return dao.getCarManufacturersAndTypes();

    }

    // same shit as above
    public List<String> getAllColors() {
        LOGGER.debug("Retrieving the available colors from the database.");
        return dao.getAllColors();
    }

    public Car getById(long id) {
        LOGGER.debug("Retrieving car from the database with the of " + id);
        return dao.findById(id);
    }

//    public Car getBySellerId(long id){
//        return dao.findBySellerId(id);
//    }

    public Collection<Car> getCarListOfSeller (long id){
        LOGGER.debug("Retrieving the list of cars of seller with " + id + " from the database.");
        return dao.getCarListOfSeller(id);
    }

    public CarLocation generateRandomLocationOnCarSave(){
        //car market boundaries;
        Double NWLat = new Double(46.750770);
        Double NWLng = new Double(23.425043);

        Double SWLat = new Double(46.749651);
        Double SWLng = new Double(23.424609);

        Double NELat = new Double(46.750696);
        Double NELng = new Double(23.430960);

        Double SELat = new Double(46.749349);
        Double SELng = new Double(23.430949);

        //random car latitude and longitude in the market boundaries
        Double safeWestLongitude = Double.max(NWLng,SWLng);
        Double safeEastLongitude = Double.min(NELng,SELng);
        Double randomCarLongitude = safeWestLongitude + (new Random().nextDouble() * (safeEastLongitude - safeWestLongitude));
        randomCarLongitude = new BigDecimal(randomCarLongitude).setScale(6,BigDecimal.ROUND_DOWN).doubleValue();

        if (randomCarLongitude > safeEastLongitude){
            randomCarLongitude = safeEastLongitude;
        }
        if (randomCarLongitude < safeWestLongitude){
            randomCarLongitude = safeWestLongitude;
        }

        Double safeNorthLatitude = Double.min(NWLat,NELat);
        Double safeSouthLatitude = Double.max(SWLat,SELat);

        Double randomCarLatitude = safeSouthLatitude + (new Random().nextDouble() * (safeNorthLatitude - safeSouthLatitude));
        randomCarLatitude = new BigDecimal(randomCarLatitude).setScale(6,BigDecimal.ROUND_UP).doubleValue();

        if (randomCarLatitude > safeNorthLatitude){
            randomCarLatitude = safeNorthLatitude;
        }
        if (randomCarLatitude < safeSouthLatitude){
            randomCarLatitude = safeSouthLatitude;
        }

        CarLocation result = new CarLocation();
        result.setLatitude(randomCarLatitude);
        result.setLongitude(randomCarLongitude);
        return result;
    }


    public void countViews(long id) {
        LOGGER.debug("Retrieving the number of view for car with id " + id);
        dao.countViews(id);
    }
}
