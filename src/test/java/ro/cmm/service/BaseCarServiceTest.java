package ro.cmm.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ro.cmm.Models.Car;
import ro.cmm.Models.CarLocation;
import ro.cmm.Models.EngineType;
import ro.cmm.Models.TransmissionType;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;



public abstract class BaseCarServiceTest {
    
    protected abstract CarService getCarService();

    protected CarLocation location = new CarLocation();


    @Before
    public void setValidCarLocation() {
        location.setLatitude(45d);
        location.setLongitude(25d);
    }

    @After
    public void tearDown() {
        Collection<Car> cars = new LinkedList<>(getCarService().listAll());

        for(Car car : cars) {
            getCarService().delete(car.getId());
        }
    }

    @Test
    public void list_all_test() throws ValidationException {
        Car car1 = new Car();
        Car car2 = new Car();

        car1.setManufacturer("Audi");
        car1.setType("A5");
        car1.setFabricationYear(2005);
        car1.setMileAge(123456);
        car1.setPrice(5000);
        car1.setEngineType(EngineType.DIESEL);
        car1.setTransmissionType(TransmissionType.MANUAL);
        car1.setColour("blue");
        car1.setExtras("Some description");
        car1.setLocation(location);
        car1.setAvailable(true);
        car1.setMatriculated(true);
        car1.setImgUrl("carpic.jpg");

        car2.setManufacturer("Mercedes");
        car2.setType("G55");
        car2.setFabricationYear(2007);
        car2.setMileAge(123457);
        car2.setPrice(50000);
        car2.setEngineType(EngineType.PETROL);
        car2.setTransmissionType(TransmissionType.AUTOMATIC);
        car2.setColour("blue");
        car2.setExtras("Some description");
        car2.setLocation(location);
        car2.setAvailable(true);
        car2.setMatriculated(true);
        car2.setImgUrl("carpic.jpg");

        getCarService().save(car1);
        getCarService().save(car2);

        Collection<Car> cars = getCarService().listAll();
        Assert.assertTrue(cars.size() == 2);
        Iterator<Car> iterator = cars.iterator();
        Assert.assertTrue(iterator.next() == car1);
        Assert.assertTrue(iterator.next() == car2);
    }

    @Test
    public void test_empty_list_all() {
        Collection<Car> cars = getCarService().listAll();
        Assert.assertTrue(cars.isEmpty());
    }

    @Test
    public void delete_successful() throws ValidationException {
        Car car1 = new Car();

        car1.setId(1);
        car1.setManufacturer("Audi");
        car1.setType("A5");
        car1.setFabricationYear(2005);
        car1.setMileAge(123456);
        car1.setPrice(5000);
        car1.setEngineType(EngineType.DIESEL);
        car1.setTransmissionType(TransmissionType.MANUAL);
        car1.setColour("blue");
        car1.setExtras("Some description");
        car1.setLocation(location);
        car1.setAvailable(true);
        car1.setMatriculated(true);
        car1.setImgUrl("carpic.jpg");

        getCarService().save(car1);
        Assert.assertTrue(getCarService().delete(car1.getId()));
    }

    @Test
    public void delete_not_existing_element() throws ValidationException {
        Car car1 = new Car();

        car1.setId(1);
        car1.setManufacturer("Audi");
        car1.setType("A5");
        car1.setFabricationYear(2005);
        car1.setMileAge(123456);
        car1.setPrice(5000);
        car1.setEngineType(EngineType.DIESEL);
        car1.setTransmissionType(TransmissionType.MANUAL);
        car1.setColour("blue");
        car1.setExtras("Some description");
        car1.setLocation(location);
        car1.setAvailable(true);
        car1.setMatriculated(true);
        car1.setImgUrl("carpic.jpg");

        getCarService().save(car1);
        getCarService().delete(car1.getId());
        Assert.assertFalse(getCarService().delete(car1.getId()));
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

//    @Test(expected = ValidationException.class)
//    public void test_add_img_url_null() throws ValidationException {
//        Car car = new Car();
//
//        location.setLatitude(45d);
//        location.setLongitude(185d);
//
//        car.setManufacturer("Audi");
//        car.setType("A5");
//        car.setFabricationYear(2005);
//        car.setMileAge(123456);
//        car.setPrice(5000);
//        car.setEngineType(EngineType.DIESEL);
//        car.setTransmissionType(TransmissionType.MANUAL);
//        car.setColour("blue");
//        car.setExtras("Some extras");
//        car.setLocation(location);
//        car.setAvailable(true);
//        car.setMatriculated(true);
//        car.setImgUrl(null);
//        getCarService().save(car);
//    }

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

}
