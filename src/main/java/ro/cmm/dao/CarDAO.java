package ro.cmm.dao;

import ro.cmm.domain.Car;
import ro.cmm.domain.SearchModel;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Emanuel Pruker
 */
public interface CarDAO extends BaseDAO<Car>{

//    Collection<Car> searchByName(String query);
//
//    Car findBySellerId(long id);

    Map<String, List<String>> getCarManufacturersAndTypes();

    List<String> getAllColors();

    Collection<Car> getCarListOfSeller(long sellerId);

    void countViews(long id);

    Collection<Car> search(SearchModel searchModel);

    Collection<Car> getLatestCars();
}

