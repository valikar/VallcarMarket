package ro.cmm.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joseph Saturday, 15.04.2017 at 01:33.
 */
public class SellerAccount extends User {

    private List<Car> carList = new ArrayList<>();

    public SellerAccount() {
        this.setRole(Role.SELLER);
    }

    public List<Car> getCarList() {
        return carList;
    }
}
