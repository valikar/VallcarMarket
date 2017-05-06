package ro.cmm.service;

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

    @Autowired
    private CarDAO dao;

    public Collection<Car> listAll() {
        return dao.getAll();
    }

    public boolean delete(Long id) {
        Car car = dao.findById(id);
        if (car != null) {
            dao.delete(car);
            return true;
        }

        return false;
    }
    public Collection<Car> search(SearchModel searchModel) {
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

    private Collection<Car> filterByManufacturer(String manufacturer, Collection<Car> cars) {

        if(!manufacturer.equalsIgnoreCase("All")) {
            cars.removeIf((Car car) -> !car.getManufacturer().equalsIgnoreCase(manufacturer));
        }
        return cars;
    }

    private Collection<Car> filterByType(String type, Collection<Car> cars) {
        if(!type.equalsIgnoreCase("All")) {
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

    private Collection<Car> filterByEngineType(List<EngineType> engineTypes, Collection<Car> cars) {

        cars.removeIf((Car car) -> !engineTypes.contains(car.getEngineType()));

        return cars;
    }

    private Collection<Car> filterByTransmissionType(List<TransmissionType> transmissionTypes, Collection<Car> cars) {

        cars.removeIf((Car car) -> !transmissionTypes.contains(car.getTransmissionType()));

        return cars;
    }

    private Collection<Car> filterByColour(String colour, Collection<Car> cars) {
        if(!colour.equalsIgnoreCase("all")) {
            cars.removeIf((Car car) -> !car.getColour().equalsIgnoreCase(colour));
        }
        return cars;
    }


    public void save(Car car) throws ValidationException {
        validate(car);car.setLocation(generateRandomLocationOnCarSave());//forced location set!
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
//        if (car.getLocation() == null) {
//            errors.add("Car location does not exist");
//        } else {
//            double carsLatitude = car.getLocation().getLatitude();
//            double carsLongitude = car.getLocation().getLongitude();
//            if (carsLatitude < 0 || carsLatitude > 90) {
//                errors.add("Invalid latitude value for the cars coordinates");
//            }
//            if (carsLongitude < 0 || carsLongitude > 180) {
//                errors.add("Invalid longitude value for the cars coordinates");
//            }
//        }
//
//        if(car.getImgUrl() == null) {
//            errors.add("Img url is empty.");
//        }

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

    // am folosit-o in ListAndSearchController pentru a incarca
    // marcile si tipurile disponibile
    // dupa ce trecem la DB real vom putea sterge/modifica metoda asta
    public Map<String,Set<String>> getManufacturersAndTypes() {
        Map<String,Set<String>> cars = new TreeMap<>();
        Set<String> audis = new TreeSet<>();
        audis.add("A4");
        audis.add("A5");
        Set<String> vw = new TreeSet<>();
        vw.add("Golf");
        vw.add("Polo");

        Set<String> ferraris = new TreeSet<>();
        ferraris.add("Laferrari");
        ferraris.add("458 Italia");

        Set<String> all = new TreeSet<>();
        all.add("All");

        cars.put("Audi",audis);
        cars.put("Vw", vw);
        cars.put("Ferrari", ferraris);
        cars.put("All", all);

        return cars;
    }

    // same shit as above
    public List<String> getAllColors() {
        List<String> colors = new LinkedList<>();
        colors.add("All");
        colors.add("Blue");
        colors.add("Red");
        colors.add("Green");
        colors.add("White");

        return colors;
    }

    public Car getById(long id) {
        return dao.findById(id);
    }

//    public Car getBySellerId(long id){
//        return dao.findBySellerId(id);
//    }

    public Collection<Car> getCarListOfSeller (long id){
        return dao.getCarListOfSeller(id);
    }

    private CarLocation generateRandomLocationOnCarSave(){
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
    dao.countViews(id);
    }
}
