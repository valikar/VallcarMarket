package ro.cmm.domain;

/**
 * @author Emanuel Pruker
 */
public class CarLocation {

    private Double latitude;
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "CarLocation{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
