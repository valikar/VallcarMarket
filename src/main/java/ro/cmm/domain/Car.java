package ro.cmm.domain;


/**
 * @author Emanuel Pruker
 */
public class Car extends AbstractModel {

    private String imgUrl;
    private String manufacturer;
    private String type;
    private int fabricationYear;
    private int mileAge;
    private int price;
    private EngineType engineType;
    private TransmissionType transmissionType;
    private String colour;
    private String extras;
    private CarLocation location;
    private boolean available;
    private boolean isMatriculated;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
