package ro.cmm.dao.imlementation;

import ro.cmm.dao.CarDAO;
import ro.cmm.Models.*;

import java.util.*;


public class IMCarDAO extends IMBaseDAO<Car> implements CarDAO {

    @Override
    public Collection<Car> getLatestCars() {
        return null;
    }


    public IMCarDAO() {

    }




    @Override
    public Collection<Car> getCarListOfSeller(long sellerId) {
        Collection<Car> cars = new LinkedList();

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
        Map<String,List<String>> cars = new TreeMap();
        List<String> audis = new LinkedList();
        audis.add("A4");
        audis.add("A5");
        List<String> vw = new LinkedList();
        vw.add("Golf");
        vw.add("Polo");

        List<String> ferraris = new LinkedList();
        ferraris.add("Laferrari");
        ferraris.add("458 Italia");

        List<String> all = new LinkedList();
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
