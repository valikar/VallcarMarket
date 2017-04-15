package ro.cmm.domain.login;

import ro.cmm.domain.User;

/**
 * Created by Joseph Saturday, 15.04.2017 at 01:34.
 */
public class BuyerAccount extends User {
    public BuyerAccount() {
        this.setRole(AccountType.BUYER);
    }
}
