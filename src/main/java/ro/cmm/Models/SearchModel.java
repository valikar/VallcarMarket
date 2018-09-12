package ro.cmm.Models;

import java.util.LinkedList;
import java.util.List;


public class SearchModel {

    public SearchModel() {
        engineType = new LinkedList();
        transmissionType = new LinkedList();
        matriculationStatus = new LinkedList();
    }

    private String manufacturer;
    private String type;
    private int fabricationYear;
    private int mileAge;
    private int price;
    private List<EngineType> engineType;
    private List<TransmissionType> transmissionType;
    private List<Boolean> matriculationStatus;
    private String colour;

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

    public List<EngineType> getEngineType() {
        return engineType;
    }

    public void setEngineType(List<EngineType> engineType) {
        this.engineType = engineType;
    }

    public List<TransmissionType> getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(List<TransmissionType> transmissionType) {
        this.transmissionType = transmissionType;
    }

    public List<Boolean> getMatriculationStatus() {
        return matriculationStatus;
    }

    public void setMatriculationStatus(List<Boolean> matriculationStatus) {
        this.matriculationStatus = matriculationStatus;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "SearchModel{" +
                "manufacturer='" + manufacturer + '\'' +
                ", type='" + type + '\'' +
                ", fabricationYear=" + fabricationYear +
                ", mileAge=" + mileAge +
                ", price=" + price +
                ", engineType=" + engineType +
                ", transmissionType=" + transmissionType +
                ", matriculationStatus=" + matriculationStatus +
                ", colour='" + colour + '\'' +
                '}';
    }
}
