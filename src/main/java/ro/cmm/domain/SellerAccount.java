package ro.cmm.domain;

import ro.cmm.domain.User;
import ro.cmm.domain.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joseph Saturday, 15.04.2017 at 01:33.
 */
public class SellerAccount extends User {

    private List<Car> carList = new ArrayList<>();

    public SellerAccount() {
        this.setRole(AccountType.SELLER);
    }

    public List<Car> getCarList() {
        return carList;
    }
}
