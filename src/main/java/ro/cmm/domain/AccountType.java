package ro.cmm.domain;

/**
 * Created by Joseph Saturday, 15.04.2017 at 01:24.
 */
public enum AccountType {
    ADMIN("Admin"),
    SELLER("Seller"),
    BUYER("Buyer");

    String type = "";

    private AccountType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
