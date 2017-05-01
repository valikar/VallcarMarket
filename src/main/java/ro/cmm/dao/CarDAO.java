package ro.cmm.dao;

import ro.cmm.domain.Car;

import java.util.Collection;
import java.util.List;

/**
 * @author Emanuel Pruker
 */
public interface CarDAO extends BaseDAO<Car>{

    Collection<Car> searchByName(String query);

    Car findBySellerId(long id);

    Collection<Car> getCarListOfSeller(long id);
}

