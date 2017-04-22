package ro.cmm.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import ro.cmm.domain.Car;
import ro.cmm.domain.CarLocation;
import ro.cmm.domain.EngineType;
import ro.cmm.domain.TransmissionType;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Tamas on 4/22/2017.
 */
public abstract class BaseCarServiceTest {

    protected abstract CarService getCarService();

    @After
    public void tearDown() {
        Collection<Car> cars = new LinkedList<>(getCarService().listAll());

        for(Car car : cars) {
            getCarService().delete(car.getId());
        }
    }

    @Test
    public void test_empty_get_all() {
        Collection<Car> cars = getCarService().listAll();
        Assert.assertTrue(cars.isEmpty());
    }

    @Test(expected = ValidationException.class)
    public void test_add_no_manufacturer() throws ValidationException {
        Car car = new Car();

        CarLocation location = new CarLocation();
        location.setLatitude(45);
        location.setLongtitude(25);

        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(5000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras("Some description");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        getCarService().save(car);
    }


    @Test(expected = ValidationException.class)
    public void test_add_no_type() throws ValidationException {
        Car car = new Car();

        CarLocation location = new CarLocation();
        location.setLatitude(45);
        location.setLongtitude(25);

        car.setManufacturer("Audi");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(5000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras("Some description");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        getCarService().save(car);
    }

    @Test(expected = ValidationException.class)
    public void test_add_fabrication_year_in_future() throws ValidationException {
        Car car = new Car();

        CarLocation location = new CarLocation();
        location.setLatitude(45);
        location.setLongtitude(25);

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2018);
        car.setMileAge(123456);
        car.setPrice(5000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras("Some description");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        getCarService().save(car);
    }

    @Test(expected = ValidationException.class)
    public void test_add_car_too_old() throws ValidationException {
        Car car = new Car();

        CarLocation location = new CarLocation();
        location.setLatitude(45);
        location.setLongtitude(25);

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(1880);
        car.setMileAge(123456);
        car.setPrice(5000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras("Some description");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        getCarService().save(car);
    }

    @Test(expected = ValidationException.class)
    public void test_add_mileAge_negative() throws ValidationException {
        Car car = new Car();

        CarLocation location = new CarLocation();
        location.setLatitude(45);
        location.setLongtitude(25);

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(-5000);
        car.setPrice(5000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras("Some description");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        getCarService().save(car);
    }

    // poate ca de asta nu avem nevoie
    @Test(expected = ValidationException.class)
    public void test_add_mileAge_too_big() throws ValidationException {
        Car car = new Car();

        CarLocation location = new CarLocation();
        location.setLatitude(45);
        location.setLongtitude(25);

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(99999999);
        car.setPrice(5000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras("Some description");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        getCarService().save(car);
    }

    @Test(expected = ValidationException.class)
    public void test_add_price_negative() throws ValidationException {
        Car car = new Car();

        CarLocation location = new CarLocation();
        location.setLatitude(45);
        location.setLongtitude(25);

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(-1000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras("Some description");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        getCarService().save(car);
    }

    // poate ca de asta nu avem nevoie
    @Test(expected = ValidationException.class)
    public void test_add_price_too_big() throws ValidationException {
        Car car = new Car();

        CarLocation location = new CarLocation();
        location.setLatitude(45);
        location.setLongtitude(25);

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(2000000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras("Some description");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        getCarService().save(car);
    }


    @Test(expected = ValidationException.class)
    public void test_add_engine_type_null() throws ValidationException {
        Car car = new Car();

        CarLocation location = new CarLocation();
        location.setLatitude(45);
        location.setLongtitude(25);

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(2000000);
        car.setEngineType(null);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras("Some description");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        getCarService().save(car);
    }

    @Test(expected = ValidationException.class)
    public void test_add_transmission_type_null() throws ValidationException {
        Car car = new Car();

        CarLocation location = new CarLocation();
        location.setLatitude(45);
        location.setLongtitude(25);

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(2000000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(null);
        car.setColour("blue");
        car.setExtras("Some description");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        getCarService().save(car);
    }

    @Test(expected = ValidationException.class)
    public void test_add_color_null() throws ValidationException {
        Car car = new Car();

        CarLocation location = new CarLocation();
        location.setLatitude(45);
        location.setLongtitude(25);

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(2000000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour(null);
        car.setExtras("Some description");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        getCarService().save(car);
    }

    @Test(expected = ValidationException.class)
    public void test_add_description_null() throws ValidationException {
        Car car = new Car();

        CarLocation location = new CarLocation();
        location.setLatitude(45);
        location.setLongtitude(25);

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(2000000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras(null);
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        getCarService().save(car);
    }

    @Test(expected = ValidationException.class)
    public void test_add_location_null() throws ValidationException {
        Car car = new Car();

        CarLocation location = new CarLocation();
        location.setLatitude(45);
        location.setLongtitude(25);

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(2000000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras("Some extras");
        car.setLocation(null);
        car.setAvailable(true);
        car.setMatriculated(true);
        getCarService().save(car);
    }

    @Test(expected = ValidationException.class)
    public void test_add_latitude_too_big() throws ValidationException {
        Car car = new Car();

        CarLocation location = new CarLocation();
        location.setLatitude(95);
        location.setLongtitude(25);

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(2000000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras("Some extras");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        getCarService().save(car);
    }

    @Test(expected = ValidationException.class)
    public void test_add_longitude_too_big() throws ValidationException {
        Car car = new Car();

        CarLocation location = new CarLocation();
        location.setLatitude(45);
        location.setLongtitude(185);

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(2000000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras("Some extras");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        getCarService().save(car);
    }

    @Test(expected = ValidationException.class)
    public void test_valid_car() throws ValidationException {
        Car car = new Car();

        CarLocation location = new CarLocation();
        location.setLatitude(45);
        location.setLongtitude(25);

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(2000000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras("Some extras");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        if(car.getId() == 0.0d) {
            throw new ValidationException("nulla");
        }
        getCarService().save(car);


//        Assert.assertEquals(1, getCarService().listAll().size());
//        Car carFromDB = getCarService().listAll().iterator().next();
//        Assert.assertNotNull(carFromDB);
//        Assert.assertTrue(carFromDB.getId() > 0);
//        car.setId(carFromDB.getId());
//        Assert.assertEquals(car, carFromDB);
    }




}
