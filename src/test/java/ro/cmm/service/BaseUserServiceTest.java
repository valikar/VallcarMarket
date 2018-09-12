package ro.cmm.service;


import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import ro.cmm.Models.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public abstract class BaseUserServiceTest {

    protected abstract UserService getUserService();

    private List<User> generateUsers(){

        User u0 = new User();
        u0.setUserName("conormcgregor@yahoo.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);

        User u1 = new User();
        u1.setUserName("conormcgregor@yahoo.com");
        u1.setPassword("password");
        u1.setPasswordValidation("password");
        u1.setFirstName("Conor");
        u1.setLastName("McGregor");
        u1.setRole(Role.SELLER);

        User u2 = new User();
        u2.setUserName("pop_iosifcristian@yahoo.com");
        u2.setPassword("password");
        u2.setPasswordValidation("password");
        u2.setFirstName("Pop");
        u2.setLastName("Iosif Cristian");
        u2.setRole(Role.BUYER);

        User u3 = new User();
        u3.setUserName("miklos_tamas@yahoo.com");
        u3.setPassword("password");
        u3.setPasswordValidation("password");
        u3.setFirstName("Miklos");
        u3.setLastName("Tamas-Norbert");
        u3.setRole(Role.SELLER);

        User u4 = new User();
        u4.setUserName("rares_scarlat@yahoo.com");
        u4.setPassword("password");
        u4.setPasswordValidation("password");
        u4.setFirstName("Rares");
        u4.setLastName("Scarlat");
        u4.setRole(Role.BUYER);

        User u5 = new User();
        u5.setUserName("emanuel_pruker@yahoo.com");
        u5.setPassword("password");
        u5.setPasswordValidation("password");
        u5.setFirstName("Emanuel");
        u5.setLastName("Pruker");
        u5.setRole(Role.SELLER);

        List<User> list = new ArrayList<>();
        list.add(u0);
        list.add(u1);
        list.add(u2);
        list.add(u3);
        list.add(u4);
        list.add(u5);

        return list;
    }

    @After
    public void tearDown(){
        Collection<User> users = new LinkedList<>(getUserService().listAll());

        for (User user : users){
            getUserService().delete(user.getId());
        }
    }


    @Test
    public void test_get_all_empty_container(){
        Collection<User> users = getUserService().listAll();
        Assert.assertTrue(users.isEmpty());
    }

    @Test(expected = ValidationException.class)
    public void test_add_no_userName() throws ValidationException{
        User u2 = new User();
        u2.setPassword("password");
        u2.setPasswordValidation("password");
        u2.setFirstName("Pop");
        u2.setLastName("Iosif Cristian");
        u2.setRole(Role.BUYER);
        getUserService().save(u2);
    }

    @Test(expected = ValidationException.class)
    public void test_add_no_firstName() throws ValidationException{
        User u2 = new User();
        u2.setUserName("pop_iosifcristian@yahoo.com");
        u2.setPassword("password");
        u2.setPasswordValidation("password");
        u2.setLastName("Iosif Cristian");
        u2.setRole(Role.BUYER);
        getUserService().save(u2);
    }

    @Test(expected = ValidationException.class)
    public void test_add_no_lastName() throws ValidationException{
        User u2 = new User();
        u2.setUserName("pop_iosifcristian@yahoo.com");
        u2.setPassword("password");
        u2.setPasswordValidation("password");
        u2.setFirstName("Pop");
        u2.setRole(Role.BUYER);
        getUserService().save(u2);
    }

    @Test(expected = ValidationException.class)
    public void test_add_no_role() throws ValidationException{
        User u2 = new User();
        u2.setUserName("pop_iosifcristian@yahoo.com");
        u2.setPassword("password");
        u2.setPasswordValidation("password");
        u2.setFirstName("Pop");
        u2.setLastName("Iosif Cristian");
        getUserService().save(u2);
    }

    @Test(expected = ValidationException.class)
    public void test_add_no_password() throws ValidationException{
        User u2 = new User();
        u2.setUserName("pop_iosifcristian@yahoo.com");
        u2.setPasswordValidation("password");
        u2.setFirstName("Pop");
        u2.setLastName("Iosif Cristian");
        u2.setRole(Role.BUYER);
        getUserService().save(u2);
    }

    @Test(expected = ValidationException.class)
    public void test_add_no_passwordValidation() throws ValidationException{
        User u2 = new User();
        u2.setUserName("pop_iosifcristian@yahoo.com");
        u2.setPassword("password");
        u2.setFirstName("Pop");
        u2.setLastName("Iosif Cristian");
        u2.setRole(Role.BUYER);
        getUserService().save(u2);
    }

    @Test(expected = ValidationException.class)
    public void test_password_not_matching() throws ValidationException{
        User u2 = new User();
        u2.setUserName("pop_iosifcristian@yahoo.com");
        u2.setPassword("password");
        u2.setPasswordValidation("drowssap");
        u2.setFirstName("Pop");
        u2.setLastName("Iosif Cristian");
        u2.setRole(Role.BUYER);
        getUserService().save(u2);
    }

    @Test(expected = ValidationException.class)
    public void add_empty() throws ValidationException {
        User user = new User();
        getUserService().save(user);
    }

    @Test
    public void test_delete_inexistent() throws ValidationException{
        Assert.assertFalse(getUserService().delete(-10l));
    }

    @Test
    public void test_serach_by_name_no_result() throws ValidationException{
        User u0 = new User();
        u0.setUserName("conormcgregor@yahoo.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);
//        Assert.assertEquals(0,getUserService().searchByUsername("Iosif").size());
    }

    @Test
    public void test_search_by_name_multiple_results() throws ValidationException {
        User u0 = new User();
        u0.setUserName("conormcgregor@yahoo.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);
        getUserService().save(u0);

        User u1 = new User();
        u1.setUserName("conormcgregor@gmail.com");
        u1.setPassword("password");
        u1.setPasswordValidation("password");
        u1.setFirstName("Conor");
        u1.setLastName("McGregor");
        u1.setRole(Role.SELLER);
        getUserService().save(u1);

//        Assert.assertEquals(2,getUserService().searchByUsername("Conor").size());
    }

    @Test
    public void test_search_by_name_multiple_results_partial_search() throws ValidationException {
        User u0 = new User();
        u0.setUserName("conormcgregor@yahoo.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);
        getUserService().save(u0);

        User u1 = new User();
        u1.setUserName("conormcgregor@gmail.com");
        u1.setPassword("password");
        u1.setPasswordValidation("password");
        u1.setFirstName("Conor");
        u1.setLastName("McGregor");
        u1.setRole(Role.SELLER);
        getUserService().save(u1);

//        Assert.assertEquals(2,getUserService().searchByUsername("Con").size());
    }

    @Test
    public void test_search_by_name_multiple_results_partial_case_insensitive() throws ValidationException {
        User u0 = new User();
        u0.setUserName("conormcgregor@yahoo.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);
        getUserService().save(u0);

        User u1 = new User();
        u1.setUserName("conormcgregor@gmail.com");
        u1.setPassword("password");
        u1.setPasswordValidation("password");
        u1.setFirstName("Conor");
        u1.setLastName("McGregor");
        u1.setRole(Role.SELLER);
        getUserService().save(u1);

        User u2 = new User();
        u2.setUserName("pop_iosifcristian@yahoo.com");
        u2.setPassword("password");
        u2.setPasswordValidation("password");
        u2.setFirstName("Pop");
        u2.setLastName("Iosif Cristian");
        u2.setRole(Role.BUYER);
        getUserService().save(u2);

//        Assert.assertEquals(2,getUserService().searchByUsername("cOn").size());
    }

    @Test
    public void test_trying_same_username() throws ValidationException {

        User u0 = new User();
        u0.setUserName("conormcgregor@yahoo.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);

        User u1 = new User();
        u1.setUserName("conormcgregor@yahoo.com");
        u1.setPassword("password");
        u1.setPasswordValidation("password");
        u1.setFirstName("Conor");
        u1.setLastName("McGregor");
        u1.setRole(Role.SELLER);

        if (getUserService().verifyUsername(u0.getUserName())){
            getUserService().save(u0);
        }
        if (getUserService().verifyUsername(u1.getUserName())){
            getUserService().save(u1);
        }

        Assert.assertEquals(1,getUserService().listAll().size());
    }

    @Test
    public void test_trying_same_username_2() throws ValidationException {

        User u0 = new User();
        u0.setUserName("conormcgregor@yahoo.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);

        User u1 = new User();
        u1.setUserName("conormcgregor@yahoo.com");
        u1.setPassword("password");
        u1.setPasswordValidation("password");
        u1.setFirstName("Conor");
        u1.setLastName("McGregor");
        u1.setRole(Role.SELLER);

        User u2 = new User();
        u2.setUserName("pop_iosifcristian@yahoo.com");
        u2.setPassword("password");
        u2.setPasswordValidation("password");
        u2.setFirstName("Pop");
        u2.setLastName("Iosif Cristian");
        u2.setRole(Role.BUYER);
        getUserService().save(u2);

        if (getUserService().verifyUsername(u0.getUserName())){
            getUserService().save(u0);
        }
        if (getUserService().verifyUsername(u1.getUserName())){
            getUserService().save(u1);
        }
        if (getUserService().verifyUsername(u2.getUserName())) {
            getUserService().save(u2);
        }
        Assert.assertEquals(2,getUserService().listAll().size());
    }

    @Test
    public void test_verify_username_empty_container() throws ValidationException {

        User u0 = new User();
        u0.setUserName("conormcgregor@yahoo.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);

        User u2 = new User();
        u2.setUserName("pop_iosifcristian@yahoo.com");
        u2.setPassword("password");
        u2.setPasswordValidation("password");
        u2.setFirstName("Pop");
        u2.setLastName("Iosif Cristian");
        u2.setRole(Role.BUYER);
        getUserService().save(u2);

        if (getUserService().verifyUsername(u0.getUserName())) {
            getUserService().save(u0);
        }
        if (getUserService().verifyUsername(u2.getUserName())) {
            getUserService().save(u2);
        }

        Assert.assertEquals(2,getUserService().listAll().size());
    }

    @Test
    public void test_verify_username() throws ValidationException {

        User u0 = new User();
        u0.setUserName("conormcgregor@yahoo.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);

        if (getUserService().verifyUsername(u0.getUserName())) {
            getUserService().save(u0);
        }

        Assert.assertEquals(1,getUserService().listAll().size());
    }

    @Test
    public void test_verify_one_item_in_list() {
        List<User> list=new ArrayList<>(generateUsers());
        try {
            getUserService().save(list.get(0));
        } catch (ValidationException e) {
            System.err.println(e);
        }
        Assert.assertEquals(1,getUserService().listAll().size());
    }

    @Test
    public void test_add_one_delete_one(){
        List<User> list=new ArrayList<>(generateUsers());
        try {
            getUserService().save(list.get(0));
        } catch (ValidationException e) {
            System.err.println(e);
        }
        List<User> users =new ArrayList<>(getUserService().listAll());
        long id=users.get(0).getId();
        getUserService().delete(id);
        Assert.assertEquals(0,getUserService().listAll().size());
    }

    @Test
    public void test_add_two_delete_one(){
        List<User> list=new ArrayList<>(generateUsers());
        try {
            getUserService().save(list.get(0));
            getUserService().save(list.get(2));
        } catch (ValidationException e) {
            System.err.println(e);
        }
        List<User> users =new ArrayList<>(getUserService().listAll());
        long id=users.get(0).getId();
        getUserService().delete(id);
        Assert.assertEquals(1,getUserService().listAll().size());
    }

    @Test
    public void test_verify_if_isRegistered() throws ValidationException {

        User u0 = new User();
        u0.setUserName("conormcgregor@yahoo.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);

        getUserService().save(u0);

        Assert.assertTrue(getUserService().isRegistered(u0.getUserName(),u0.getPassword()));
    }

    @Test
    public void test_verify_if_isRegistered_2() throws ValidationException {

        User u0 = new User();
        u0.setUserName("conormcgregor@yahoo.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);

        Assert.assertFalse(getUserService().isRegistered(u0.getUserName(),u0.getPassword()));
    }

    @Test
    public void test_get_all_sellers() throws ValidationException {

        User u0 = new User();
        u0.setUserName("conormcgregor@yahoo.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);
        getUserService().save(u0);

        User u2 = new User();
        u2.setUserName("pop_iosifcristian@yahoo.com");
        u2.setPassword("password");
        u2.setPasswordValidation("password");
        u2.setFirstName("Pop");
        u2.setLastName("Iosif Cristian");
        u2.setRole(Role.BUYER);
        getUserService().save(u2);

        Assert.assertEquals(1, getUserService().listAllSellers().size());
    }

    @Test
    public void test_get_all_sellers_2() throws ValidationException {

        User u2 = new User();
        u2.setUserName("pop_iosifcristian@yahoo.com");
        u2.setPassword("password");
        u2.setPasswordValidation("password");
        u2.setFirstName("Pop");
        u2.setLastName("Iosif Cristian");
        u2.setRole(Role.BUYER);
        getUserService().save(u2);

        Assert.assertEquals(0, getUserService().listAllSellers().size());
    }

    @Test
    public void test_get_all_sellers_3() throws ValidationException {

        User u0 = new User();
        u0.setUserName("conormcgregor@yahoo.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);
        getUserService().save(u0);

        User u2 = new User();
        u2.setUserName("pop_iosifcristian@yahoo.com");
        u2.setPassword("password");
        u2.setPasswordValidation("password");
        u2.setFirstName("Pop");
        u2.setLastName("Iosif Cristian");
        u2.setRole(Role.SELLER);
        getUserService().save(u2);

        User u4 = new User();
        u4.setUserName("rares_scarlat@yahoo.com");
        u4.setPassword("password");
        u4.setPasswordValidation("password");
        u4.setFirstName("Rares");
        u4.setLastName("Scarlat");
        u4.setRole(Role.BUYER);
        getUserService().save(u4);

        User u5 = new User();
        u5.setUserName("emanuel_pruker@yahoo.com");
        u5.setPassword("password");
        u5.setPasswordValidation("password");
        u5.setFirstName("Emanuel");
        u5.setLastName("Pruker");
        u5.setRole(Role.SELLER);
        getUserService().save(u5);

        Assert.assertEquals(3, getUserService().listAllSellers().size());
    }

    @Test
    public void test_get_all_buyers() throws ValidationException {

        User u0 = new User();
        u0.setUserName("conormcgregor@yahoo.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);
        getUserService().save(u0);

        User u2 = new User();
        u2.setUserName("pop_iosifcristian@yahoo.com");
        u2.setPassword("password");
        u2.setPasswordValidation("password");
        u2.setFirstName("Pop");
        u2.setLastName("Iosif Cristian");
        u2.setRole(Role.BUYER);
        getUserService().save(u2);

        Assert.assertEquals(1, getUserService().listAllBuyers().size());
    }

    @Test
    public void test_get_all_buyers_2() throws ValidationException {

        User u2 = new User();
        u2.setUserName("pop_iosifcristian@yahoo.com");
        u2.setPassword("password");
        u2.setPasswordValidation("password");
        u2.setFirstName("Pop");
        u2.setLastName("Iosif Cristian");
        u2.setRole(Role.SELLER);
        getUserService().save(u2);

        Assert.assertEquals(0, getUserService().listAllBuyers().size());
    }

    @Test
    public void test_get_all_buyers_3() throws ValidationException {

        User u0 = new User();
        u0.setUserName("conormcgregor@yahoo.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);
        getUserService().save(u0);

        User u2 = new User();
        u2.setUserName("pop_iosifcristian@yahoo.com");
        u2.setPassword("password");
        u2.setPasswordValidation("password");
        u2.setFirstName("Pop");
        u2.setLastName("Iosif Cristian");
        u2.setRole(Role.BUYER);
        getUserService().save(u2);

        User u4 = new User();
        u4.setUserName("rares_scarlat@yahoo.com");
        u4.setPassword("password");
        u4.setPasswordValidation("password");
        u4.setFirstName("Rares");
        u4.setLastName("Scarlat");
        u4.setRole(Role.BUYER);
        getUserService().save(u4);

        User u5 = new User();
        u5.setUserName("emanuel_pruker@yahoo.com");
        u5.setPassword("password");
        u5.setPasswordValidation("password");
        u5.setFirstName("Emanuel");
        u5.setLastName("Pruker");
        u5.setRole(Role.SELLER);
        getUserService().save(u5);

        Assert.assertEquals(2, getUserService().listAllBuyers().size());
    }

    @Test
    public void test_add_bookmark() throws ValidationException {
        User u0 = new User();
        u0.setUserName("conormcgregor@yahoo.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);
        getUserService().save(u0);
        User user =getUserService().getDao().findByUsername(u0.getUserName());
        u0.setId(user.getId());
        getUserService().addBookmark(1,u0.getId());

        Assert.assertEquals(1,getUserService().getBookmarks(u0.getId()).size());
    }

    @Test
    public void test_add_and_delete_bookmark() throws ValidationException {
        User u0 = new User();
        u0.setUserName("conormcgregor@yahoo.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);
        getUserService().save(u0);
        User user =getUserService().getDao().findByUsername(u0.getUserName());
        u0.setId(user.getId());

        getUserService().addBookmark(1,u0.getId());
        getUserService().deleteBookmark(1,u0.getId());

        Assert.assertEquals(0,getUserService().getBookmarks(u0.getId()).size());
    }

    @Test
    public void test_add_two_and_delete_one_bookmark() throws ValidationException {
        User u0 = new User();
        u0.setUserName("conormcgregor@yahoo.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);
        getUserService().save(u0);
        User user =getUserService().getDao().findByUsername(u0.getUserName());
        u0.setId(user.getId());
        getUserService().addBookmark(1,u0.getId());
        getUserService().addBookmark(2,u0.getId());
        getUserService().deleteBookmark(1,u0.getId());

        Assert.assertEquals(1,getUserService().getBookmarks(u0.getId()).size());
    }

    @Test
    public void test_add_same_bookmark() throws ValidationException {
        User u0 = new User();
        u0.setUserName("conormcgregor@yahoo.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);
        getUserService().save(u0);

        User user =getUserService().getDao().findByUsername(u0.getUserName());
        u0.setId(user.getId());

        getUserService().addBookmark(1,u0.getId());
        getUserService().addBookmark(1,u0.getId());
        getUserService().deleteBookmark(1,u0.getId());

        Assert.assertEquals(0,getUserService().getBookmarks(u0.getId()).size());
    }
}
