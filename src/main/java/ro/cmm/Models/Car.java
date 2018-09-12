package ro.cmm.Models;



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
    private Boolean available;
    private Boolean matriculated;

    private int views;
    private long sellerId;

    private int distance;

    public Car(){
        this.available=false;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getFabricationYear() {
        return fabricationYear;
    }

    public void setFabricationYear(int fabricationYear) {
        this.fabricationYear = fabricationYear;
    }

    public int getMileAge() {
        return mileAge;
    }

    public void setMileAge(int mileAge) {
        this.mileAge = mileAge;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public CarLocation getLocation() {
        return location;
    }

    public void setLocation(CarLocation location) {
        this.location = location;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Boolean getMatriculated() {
        return matriculated;
    }

    public void setMatriculated(Boolean matriculated) {
        this.matriculated = matriculated;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Car{" +
                "imgUrl='" + imgUrl + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", type='" + type + '\'' +
                ", fabricationYear=" + fabricationYear +
                ", mileAge=" + mileAge +
                ", price=" + price +
                ", engineType=" + engineType +
                ", transmissionType=" + transmissionType +
                ", colour='" + colour + '\'' +
                ", extras='" + extras + '\'' +
                ", location=" + location +
                ", isMatriculated=" + matriculated +
                '}';
    }
}
