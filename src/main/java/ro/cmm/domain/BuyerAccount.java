package ro.cmm.domain;

/**
 * Created by Joseph Saturday, 15.04.2017 at 01:34.
 */
public class BuyerAccount extends User {
    public BuyerAccount() {
        this.setRole(Role.BUYER);
    }
}
