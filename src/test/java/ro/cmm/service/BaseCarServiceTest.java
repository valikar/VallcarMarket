package ro.cmm.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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

    protected CarLocation location = new CarLocation();

    @Before
    public void setValidCarLocation() {
        location.setLatitude(45);
        location.setLongtitude(25);
    }

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
        car.setImgUrl("carpic.jpg");
        getCarService().save(car);
    }


    @Test(expected = ValidationException.class)
    public void test_add_no_type() throws ValidationException {
        Car car = new Car();

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
        car.setImgUrl("carpic.jpg");
        getCarService().save(car);
    }

    @Test(expected = ValidationException.class)
    public void test_add_fabrication_year_in_future() throws ValidationException {
        Car car = new Car();

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
        car.setImgUrl("carpic.jpg");
        getCarService().save(car);
    }

    @Test(expected = ValidationException.class)
    public void test_add_car_too_old() throws ValidationException {
        Car car = new Car();

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
        car.setImgUrl("carpic.jpg");
        getCarService().save(car);
    }

    @Test(expected = ValidationException.class)
    public void test_add_mileAge_negative() throws ValidationException {
        Car car = new Car();

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
        car.setImgUrl("carpic.jpg");
        getCarService().save(car);
    }

    // poate ca de asta nu avem nevoie
    @Test(expected = ValidationException.class)
    public void test_add_mileAge_too_big() throws ValidationException {
        Car car = new Car();

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
        car.setImgUrl("carpic.jpg");
        getCarService().save(car);
    }

    @Test(expected = ValidationException.class)
    public void test_add_price_negative() throws ValidationException {
        Car car = new Car();

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
        car.setImgUrl("carpic.jpg");
        getCarService().save(car);
    }

    // poate ca de asta nu avem nevoie
    @Test(expected = ValidationException.class)
    public void test_add_price_too_big() throws ValidationException {
        Car car = new Car();

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
        car.setImgUrl("carpic.jpg");
        getCarService().save(car);
    }


    @Test(expected = ValidationException.class)
    public void test_add_engine_type_null() throws ValidationException {
        Car car = new Car();

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(5000);
        car.setEngineType(null);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras("Some description");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        car.setImgUrl("carpic.jpg");
        getCarService().save(car);
    }

    @Test(expected = ValidationException.class)
    public void test_add_transmission_type_null() throws ValidationException {
        Car car = new Car();

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(5000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(null);
        car.setColour("blue");
        car.setExtras("Some description");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        car.setImgUrl("carpic.jpg");
        getCarService().save(car);
    }

    @Test(expected = ValidationException.class)
    public void test_add_color_null() throws ValidationException {
        Car car = new Car();

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(5000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour(null);
        car.setExtras("Some description");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        car.setImgUrl("carpic.jpg");
        getCarService().save(car);
    }

    @Test(expected = ValidationException.class)
    public void test_add_description_null() throws ValidationException {
        Car car = new Car();

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(5000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras(null);
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        car.setImgUrl("carpic.jpg");
        getCarService().save(car);
    }

    @Test(expected = ValidationException.class)
    public void test_add_location_null() throws ValidationException {
        Car car = new Car();

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(5000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras("Some extras");
        car.setLocation(null);
        car.setAvailable(true);
        car.setMatriculated(true);
        car.setImgUrl("carpic.jpg");
        getCarService().save(car);
    }

    @Test(expected = ValidationException.class)
    public void test_add_latitude_too_big() throws ValidationException {
        Car car = new Car();

        location.setLatitude(95);
        location.setLongtitude(25);

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(5000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras("Some extras");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        car.setImgUrl("carpic.jpg");
        getCarService().save(car);
    }

    @Test(expected = ValidationException.class)
    public void test_add_longitude_too_big() throws ValidationException {
        Car car = new Car();

        location.setLatitude(45);
        location.setLongtitude(185);

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(5000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras("Some extras");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        car.setImgUrl("carpic.jpg");
        getCarService().save(car);
    }

    @Test(expected = ValidationException.class)
    public void test_add_img_url_null() throws ValidationException {
        Car car = new Car();

        location.setLatitude(45);
        location.setLongtitude(185);

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(5000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras("Some extras");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        car.setImgUrl(null);
        getCarService().save(car);
    }

    @Test
    public void test_valid_car() throws ValidationException {
        Car car = new Car();

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(5000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras("Some extras");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        car.setImgUrl("carpic.jpg");
        getCarService().save(car);

        Assert.assertEquals(1, getCarService().listAll().size());
        Car carFromDB = getCarService().listAll().iterator().next();
        Assert.assertNotNull(carFromDB);
        Assert.assertTrue(carFromDB.getId() > 0);
        car.setId(carFromDB.getId());
        Assert.assertEquals(car, carFromDB);
    }

    @Test
    public void test_delete_inexistent() {
        Assert.assertFalse(getCarService().delete(-100l));
    }

    @Test
    public void test_add_delete() throws ValidationException {
        Car car = new Car();

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(5000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras("Some extras");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        car.setImgUrl("carpic.jpg");
        getCarService().save(car);
        Assert.assertEquals(1, getCarService().listAll().size());
        Car carFromDb = getCarService().listAll().iterator().next();

        Assert.assertTrue(getCarService().delete(car.getId()));
        Assert.assertFalse(getCarService().delete(car.getId()));
        Assert.assertEquals(0, getCarService().listAll().size());
    }

    @Test
    public void test_big_search() throws ValidationException {
        Car car = new Car();

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(5000);
        car.setEngineType(EngineType.DIESEL);
        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras("Some extras");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        car.setImgUrl("carpic.jpg");
        getCarService().save(car);

        Car car2 = new Car();
        car2.setManufacturer("Audi");
        car2.setType("A5");
        car2.setFabricationYear(2005);
        car2.setMileAge(123459);
        car2.setPrice(6000);
        car2.setEngineType(EngineType.PETROL);
        car2.setTransmissionType(TransmissionType.AUTOMATIC);
        car2.setColour("blue");
        car2.setExtras("Some more extras");
        car2.setLocation(location);
        car2.setAvailable(true);
        car2.setMatriculated(true);
        car2.setImgUrl("carpic.jpg");
        getCarService().save(car2);

        EngineType[] engineTypes = {EngineType.DIESEL, EngineType.PETROL};
        TransmissionType[] transmissionTypes = {TransmissionType.MANUAL, TransmissionType.AUTOMATIC};

        Collection<Car> cars = getCarService().search("audi","a5", 2005,150000,6000,engineTypes,
                                                        transmissionTypes, "blue");
        Assert.assertEquals(2, cars.size());
    }


}
