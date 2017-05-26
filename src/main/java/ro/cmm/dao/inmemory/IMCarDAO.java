package ro.cmm.dao.inmemory;

import ro.cmm.dao.CarDAO;
import ro.cmm.domain.*;

import java.util.*;

/**
 * @author Emanuel Pruker
 */

public class IMCarDAO extends IMBaseDAO<Car> implements CarDAO {

    @Override
    public Collection<Car> getLatestCars() {
        return null;
    }

    // strict pentru testing, dupa ce vom trece la DB vom putea sterge constructorul de mai jos
    public IMCarDAO() {
//        CarLocation location = new CarLocation();
//        location.setLatitude(45d);
//        location.setLongitude(25d);
//
//        Car car = new Car();
//        car.setManufacturer("Audi");
//        car.setType("A5");
//        car.setFabricationYear(2005);
//        car.setMileAge(12000);
//        car.setPrice(5000);
//        car.setEngineType(EngineType.DIESEL);
//        car.setTransmissionType(TransmissionType.MANUAL);
//        car.setColour("Blue");
//        car.setExtras("Some extras");
//        car.setLocation(location);
//        car.setAvailable(true);
//        car.setMatriculated(true);
//        car.setImgUrl("carpic.jpg");
//
//        Car car2 = new Car();
//        car2.setManufacturer("Audi");
//        car2.setType("A4");
//        car2.setFabricationYear(2010);
//        car2.setMileAge(50000);
//        car2.setPrice(6000);
//        car2.setEngineType(EngineType.PETROL);
//        car2.setTransmissionType(TransmissionType.AUTOMATIC);
//        car2.setColour("Blue");
//        car2.setExtras("Some more extras");
//        car2.setLocation(location);
//        car2.setAvailable(true);
//        car2.setMatriculated(true);
//        car2.setImgUrl("carpic2.jpg");
//
//        Car car3 = new Car();
//        car3.setManufacturer("Vw");
//        car3.setType("Golf");
//        car3.setFabricationYear(2007);
//        car3.setMileAge(50000);
//        car3.setPrice(6000);
//        car3.setEngineType(EngineType.PETROL);
//        car3.setTransmissionType(TransmissionType.AUTOMATIC);
//        car3.setColour("Red");
//        car3.setExtras("Some more extras");
//        car3.setLocation(location);
//        car3.setAvailable(true);
//        car3.setMatriculated(true);
//        car3.setImgUrl("carpic2.jpg");
//
//        update(car);
//        update(car2);
//        update(car3);
    }

//    @Override
//    public Collection<Car> searchByName(String query) {
//        if (StringUtils.isEmpty(query)) {
//            return getAll();
//        }
//
//        Collection<Car> all = new LinkedList<Car>(getAll());
//        for (Iterator<Car> it = all.iterator(); it.hasNext();) {
//            Car car = it.next();
//            String ss = car.getManufacturer() + " " + car.getType();
//            if (!ss.toLowerCase().contains(query.toLowerCase())) {
//                it.remove();
//            }
//        }
//        return all;
//    }
//
//    @Override
//    public Car findBySellerId(long id) {
//        for (Car car : getAll()){
//            if (car.getSellerId()==id)
//                return car;
//        }
//        return null;
//    }

    @Override
    public Collection<Car> getCarListOfSeller(long sellerId) {
        Collection<Car> cars = new LinkedList<>();

        for (Car car : getAll()){
            if (car.getSellerId()== sellerId){
                cars.add(car);
            }
        }
        return cars;
    }

    @Override
    public Collection<Car> search(SearchModel searchModel) {
        return null;
    }

    @Override
    public void countViews(long id) {
        Car car = findById(id);
        car.setViews(car.getViews()+1);
        update(car);
    }

    @Override
    public Map<String, List<String>> getCarManufacturersAndTypes() {
        Map<String,List<String>> cars = new TreeMap<>();
        List<String> audis = new LinkedList<>();
        audis.add("A4");
        audis.add("A5");
        List<String> vw = new LinkedList<>();
        vw.add("Golf");
        vw.add("Polo");

        List<String> ferraris = new LinkedList<>();
        ferraris.add("Laferrari");
        ferraris.add("458 Italia");

        List<String> all = new LinkedList<>();
        all.add("All");

        cars.put("Audi",audis);
        cars.put("Vw", vw);
        cars.put("Ferrari", ferraris);
        cars.put("All", all);
        return cars;
    }

    @Override
    public List<String> getAllColors() {
        return null;
    }
}
