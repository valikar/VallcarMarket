package ro.cmm.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ro.cmm.domain.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tamas on 4/22/2017.
 */

public abstract class BaseCarServiceTest {
    
    protected abstract CarService getCarService();

    protected CarLocation location = new CarLocation();

    protected SearchModel searchAllModel;

    protected Car audiA4;
    protected Car audiA5;
    protected Car vwGolf;
    protected Car vwPolo;
    protected Car laFerrari;

    public void generateCars() {
        searchAllModel = new SearchModel();
        searchAllModel.setManufacturer("All");
        searchAllModel.setType("All");
        List<EngineType> engineTypes = new LinkedList<>();
        engineTypes.add(EngineType.PETROL);
        engineTypes.add(EngineType.DIESEL);
        engineTypes.add(EngineType.HYBRID);
        searchAllModel.setEngineType(engineTypes);
        List<TransmissionType> transmissionTypes = new LinkedList<>();
        transmissionTypes.add(TransmissionType.AUTOMATIC);
        transmissionTypes.add(TransmissionType.MANUAL);
        searchAllModel.setTransmissionType(transmissionTypes);
        searchAllModel.setColour("All");

        audiA4 = new Car();
        audiA4.setManufacturer("Audi");
        audiA4.setType("A4");
        audiA4.setFabricationYear(2005);
        audiA4.setMileAge(123456);
        audiA4.setPrice(5000);
        audiA4.setEngineType(EngineType.DIESEL);
        audiA4.setTransmissionType(TransmissionType.MANUAL);
        audiA4.setColour("Blue");
        audiA4.setExtras("Some extras");
        audiA4.setLocation(location);
        audiA4.setAvailable(true);
        audiA4.setMatriculated(true);
        audiA4.setImgUrl("carpic.jpg");

        audiA5 = new Car();
        audiA5.setManufacturer("Audi");
        audiA5.setType("A5");
        audiA5.setFabricationYear(2005);
        audiA5.setMileAge(123459);
        audiA5.setPrice(6000);
        audiA5.setEngineType(EngineType.PETROL);
        audiA5.setTransmissionType(TransmissionType.AUTOMATIC);
        audiA5.setColour("Blue");
        audiA5.setExtras("Some more extras");
        audiA5.setLocation(location);
        audiA5.setAvailable(true);
        audiA5.setMatriculated(true);
        audiA5.setImgUrl("carpic2.jpg");

        vwGolf = new Car();
        vwGolf.setManufacturer("Vw");
        vwGolf.setType("Golf");
        vwGolf.setFabricationYear(2000);
        vwGolf.setMileAge(200000);
        vwGolf.setPrice(3000);
        vwGolf.setEngineType(EngineType.PETROL);
        vwGolf.setTransmissionType(TransmissionType.AUTOMATIC);
        vwGolf.setColour("Red");
        vwGolf.setExtras("Some more extras");
        vwGolf.setLocation(location);
        vwGolf.setAvailable(true);
        vwGolf.setMatriculated(true);
        vwGolf.setImgUrl("carpic3.jpg");

        vwPolo = new Car();
        vwPolo.setManufacturer("Vw");
        vwPolo.setType("Polo");
        vwPolo.setFabricationYear(2010);
        vwPolo.setMileAge(50000);
        vwPolo.setPrice(8000);
        vwPolo.setEngineType(EngineType.PETROL);
        vwPolo.setTransmissionType(TransmissionType.AUTOMATIC);
        vwPolo.setColour("Green");
        vwPolo.setExtras("Some more extras");
        vwPolo.setLocation(location);
        vwPolo.setAvailable(true);
        vwPolo.setMatriculated(true);
        vwPolo.setImgUrl("carpic4.jpg");

        laFerrari = new Car();
        laFerrari.setManufacturer("Ferrari");
        laFerrari.setType("LaFerrari");
        laFerrari.setFabricationYear(2016);
        laFerrari.setMileAge(10000);
        laFerrari.setPrice(500000);
        laFerrari.setEngineType(EngineType.HYBRID);
        laFerrari.setTransmissionType(TransmissionType.AUTOMATIC);
        laFerrari.setColour("Red");
        laFerrari.setExtras("Some more extras");
        laFerrari.setLocation(location);
        laFerrari.setAvailable(true);
        laFerrari.setMatriculated(true);
        laFerrari.setImgUrl("carpic5.jpg");


    }


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

        location.setLatitude(95d);
        location.setLongitude(25d);

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

        location.setLatitude(45d);
        location.setLongitude(185d);

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

        location.setLatitude(45d);
        location.setLongitude(185d);

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

        List<EngineType> engineTypes = new LinkedList<>();
        engineTypes.add(EngineType.PETROL);
        engineTypes.add(EngineType.DIESEL);
        List<TransmissionType> transmissionTypes = new LinkedList<>();
        transmissionTypes.add(TransmissionType.MANUAL);
        transmissionTypes.add(TransmissionType.AUTOMATIC);

        Collection<Car> cars = getCarService().search("audi","a5", 2005,150000,6000,engineTypes,
                                                        transmissionTypes, "blue");
        Assert.assertEquals(2, cars.size());
    }

    @Test
    public void searchAllCars() throws ValidationException {
        generateCars();
        getCarService().save(vwGolf);
        getCarService().save(vwPolo);
        getCarService().save(audiA4);
        getCarService().save(audiA5);
        getCarService().save(laFerrari);
        Collection<Car> allCarsInDb = getCarService().search(searchAllModel);
        Assert.assertTrue(allCarsInDb.size() == 5);

        searchAllModel.setManufacturer("Audi");
        allCarsInDb = getCarService().search(searchAllModel);
        Assert.assertTrue(allCarsInDb.size() == 2);

        searchAllModel.setManufacturer("valami");
        allCarsInDb = getCarService().search(searchAllModel);
        System.out.println(allCarsInDb.size());
        Assert.assertTrue(allCarsInDb.size() == 0);

        searchAllModel.setManufacturer("Vw");
        allCarsInDb = getCarService().search(searchAllModel);
        System.out.println(searchAllModel);
        System.out.println(allCarsInDb.size());
        Assert.assertTrue(allCarsInDb.size() == 2);
    }

    @Test
    public void filter_by_manufacturer() throws ValidationException{
        Car car = new Car();

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(5000);
        car.setEngineType(EngineType.DIESEL);
//        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras("Some extras");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        car.setImgUrl("carpic.jpg");
//        getCarService().save(car);

        SearchModel searchModel = new SearchModel();
        searchModel.setManufacturer("Audi");
        searchModel.setType("A5");
        List<EngineType> engineTypes = new LinkedList<>();
        engineTypes.add(EngineType.PETROL);
        engineTypes.add(EngineType.DIESEL);
        searchModel.setEngineType(engineTypes);
        List<TransmissionType> transmissionTypes = new LinkedList<>();
        transmissionTypes.add(TransmissionType.AUTOMATIC);
        transmissionTypes.add(TransmissionType.MANUAL);
        searchModel.setTransmissionType(transmissionTypes);

        Collection<Car> cars = getCarService().search(searchModel);
        Assert.assertTrue(cars.size() == 1);
        Assert.assertTrue(cars.iterator().next().getManufacturer().equals(searchModel.getManufacturer()));

    }

    @Test
    public void filter_by_type() throws ValidationException{
        Car car = new Car();

        car.setManufacturer("Audi");
        car.setType("A5");
        car.setFabricationYear(2005);
        car.setMileAge(123456);
        car.setPrice(5000);
        car.setEngineType(EngineType.DIESEL);
//        car.setTransmissionType(TransmissionType.MANUAL);
        car.setColour("blue");
        car.setExtras("Some extras");
        car.setLocation(location);
        car.setAvailable(true);
        car.setMatriculated(true);
        car.setImgUrl("carpic.jpg");
//        getCarService().save(car);

        SearchModel searchModel = new SearchModel();
        searchModel.setManufacturer("Audi");
        searchModel.setType("A5");
        List<EngineType> engineTypes = new LinkedList<>();
        engineTypes.add(EngineType.PETROL);
        engineTypes.add(EngineType.DIESEL);
        searchModel.setEngineType(engineTypes);
        List<TransmissionType> transmissionTypes = new LinkedList<>();
        transmissionTypes.add(TransmissionType.AUTOMATIC);
        transmissionTypes.add(TransmissionType.MANUAL);
        searchModel.setTransmissionType(transmissionTypes);

        Collection<Car> cars = getCarService().search(searchModel);

        Assert.assertTrue(cars.size() == 2);
        Assert.assertTrue(cars.iterator().next().getType().equals(searchModel.getType()));
    }


}
