package ro.cmm.domain;

import ro.cmm.domain.login.AccountType;

/**
 * Created by Joseph Saturday, 15.04.2017 at 01:13.
 */
public abstract class User extends AbstractModel {

    private String userName;//email
    private String password;

    private AccountType role;

    private String firstName;
    private String lastName;

    private boolean authenticated;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountType getRole() {
        return role;
    }

    public void setRole(AccountType role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (authenticated != user.authenticated) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (role != user.role) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        return lastName != null ? lastName.equals(user.lastName) : user.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (authenticated ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User[" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", authenticated=" + authenticated +
                ']';
    }
}
