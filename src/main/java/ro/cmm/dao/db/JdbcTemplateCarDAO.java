package ro.cmm.dao.db;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import ro.cmm.dao.CarDAO;
import ro.cmm.domain.Car;
import ro.cmm.domain.EngineType;
import ro.cmm.domain.TransmissionType;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tamas on 5/4/2017.
 */
public class JdbcTemplateCarDAO implements CarDAO {

    private JdbcTemplate jdbcTemplate;

    private String carDetailsForQuery = "Select c.id, " +
                                        "cm.manufacturer_name, " +
                                        "ct.type_name, " +
                                        "c.price, " +
                                        "c.mileage, " +
                                        "c.registration_year, " +
                                        "c.extras, " +
                                        "et.engine_type, " +
                                        "tt.transmission_type, " +
                                        "co.colour, " +
                                        "c.matriculation_status, " +
                                        "cp.picture_src " +

                                        "from cars c join car_manufacturers cm on c.manufacturer_id = cm.id "+
                                        "join car_types ct on c.type_id = ct.id "+
                                        "join engine_types et on c.engine_type_id = et.id "+
                                        "join transmission_types tt on c.transmission_type_id = tt.id "+
                                        "join colours co on c.colour_id = co.id " +
                                        "join car_pictures cp on c.id = cp.car_id";

    public JdbcTemplateCarDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<Car> getAll() {
        String query = carDetailsForQuery;

        return jdbcTemplate.query(query, new CarResultSetExtractor());
    }

    @Override
    public Collection<Car> searchByName(String query) {
        return null;
    }

    @Override
    public Car findById(Long id) {
        String query = carDetailsForQuery +
                "where id = ?";

        return null;
    }

    @Override
    public Car update(Car model) {
        return null;
    }

    @Override
    public Car findBySellerId(long id) {
        return null;
    }

    @Override
    public boolean delete(Car model) {
        return false;
    }

    @Override
    public Collection<Car> getCarListOfSeller(long id) {
        return null;
    }

    @Override
    public void countViews(long id) {

    }
//
//    private static class CarMapper implements RowMapper<Car> {
//        @Override
//        public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Car car = new Car();
//            car.setId(rs.getLong("id"));
//            car.setManufacturer(rs.getString("manufacturer_name"));
//            car.setType(rs.getString("type_name"));
//            car.setPrice(rs.getInt("price"));
//            car.setMileAge(rs.getInt("mileage"));
//            car.setFabricationYear(rs.getInt("registration_year"));
//            car.setExtras(rs.getString("extras"));
//            car.setEngineType(EngineType.valueOf(rs.getString("engine_type")));
//            car.setTransmissionType(TransmissionType.valueOf(rs.getString("transmission_type")));
//            car.setColour(rs.getString("colour"));
//            // car.setLocation(ceva)
//            car.setMatriculated(rs.getBoolean("matriculation_status"));
//            car.setImgUrl(rs.getString("picture_src"));
//
//            return car;
//        }
//    }

    private static class CarResultSetExtractor implements ResultSetExtractor<Collection<Car>> {
        @Override
        public Collection<Car> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Long,Car> cars = new HashMap<>();
            System.out.println("valami");
            while (rs.next()) {
                System.out.println("Itt vajon megy?");
                long id = rs.getLong("id");
                if(cars.keySet().contains(id)) {
                    // in acest caz trebuie doar adaugata imaginea fara sa cream un nou car
                    // cars.get(id).addImgurl(rs.getString("picture_src"));
                } else {
                    Car car = new Car();
                    car.setId(id);
                    car.setManufacturer(rs.getString("manufacturer_name"));
                    car.setType(rs.getString("type_name"));
                    car.setPrice(rs.getInt("price"));
                    car.setMileAge(rs.getInt("mileage"));
                    car.setFabricationYear(rs.getInt("registration_year"));
                    car.setExtras(rs.getString("extras"));
                    car.setEngineType(EngineType.valueOf(rs.getString("engine_type")));
                    car.setTransmissionType(TransmissionType.valueOf(rs.getString("transmission_type")));
                    car.setColour(rs.getString("colour"));
                    // car.setLocation(ceva)
                    car.setMatriculated(rs.getBoolean("matriculation_status"));
                    car.setImgUrl(rs.getString("picture_src"));

                    System.out.println(car);
                    cars.put(id, car);
                }
            }
            return cars.values();
        }
    }

}
