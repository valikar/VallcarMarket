package ro.cmm.dao.inmemory;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ro.cmm.dao.CarDAO;
import ro.cmm.domain.Car;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Emanuel Pruker
 */
@Component
public class IMCarDAO extends IMBaseDAO<Car> implements CarDAO {

    @Override
    public Collection<Car> searchByName(String query) {
        if (StringUtils.isEmpty(query)) {
            return getAll();
        }

        Collection<Car> all = new LinkedList<Car>(getAll());
        for (Iterator<Car> it = all.iterator(); it.hasNext();) {
            Car car = it.next();
            String ss = car.getManufacturer() + " " + car.getType();
            if (!ss.toLowerCase().contains(query.toLowerCase())) {
                it.remove();
            }
        }
        return all;
    }
}
