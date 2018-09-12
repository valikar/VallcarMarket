package ro.cmm.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import ro.cmm.Models.*;

import java.util.LinkedList;
import java.util.List;


public abstract class BaseMessageServiceTest {

    protected abstract MessageService getMessageService();
    protected abstract UserService getUserService();
    protected abstract CarService getCarService();


    @After
    public void tearDown(){
        getMessageService().getDao().clearMemory();
    }

    @Test
    public void test_verify_conversation() throws ValidationException {
        User u1 = new User();
        u1.setUserName("conormcgregor@yahoo.com");
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
        List<User> userList = new LinkedList<>(getUserService().listAll());
        u1.setId(userList.get(0).getId());
        u2.setId(userList.get(1).getId());

        CarLocation location = new CarLocation();
        location.setLatitude(45d);
        location.setLongitude(25d);

        Car c1 = new Car();
        c1.setManufacturer("Audi");
        c1.setType("A5");
        c1.setFabricationYear(2005);
        c1.setMileAge(123456);
        c1.setPrice(5000);
        c1.setEngineType(EngineType.DIESEL);
        c1.setTransmissionType(TransmissionType.MANUAL);
        c1.setColour("blue");
        c1.setExtras("Some extras");
        c1.setLocation(location);
        c1.setAvailable(true);
        c1.setMatriculated(true);
        c1.setImgUrl("carpic.jpg");
        c1.setSellerId(u1.getId());
        getCarService().save(c1);

        Conversation conversation = new Conversation();
        conversation.setSenderId(u2.getId());
        conversation.setReceiverId(u1.getId());
        conversation.setTitle(c1.getManufacturer()+" "+c1.getType());

        Assert.assertTrue(getMessageService().verifyConversation(conversation));
    }

    @Test
    public void test_add_new_conversation() throws ValidationException {
        User u1 = new User();
        u1.setUserName("conormcgregor@yahoo.com");
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
        List<User> userList = new LinkedList<>(getUserService().listAll());
        u1.setId(userList.get(0).getId());
        u2.setId(userList.get(1).getId());

        CarLocation location = new CarLocation();
        location.setLatitude(45d);
        location.setLongitude(25d);

        Car c1 = new Car();
        c1.setManufacturer("Audi");
        c1.setType("A5");
        c1.setFabricationYear(2005);
        c1.setMileAge(123456);
        c1.setPrice(5000);
        c1.setEngineType(EngineType.DIESEL);
        c1.setTransmissionType(TransmissionType.MANUAL);
        c1.setColour("blue");
        c1.setExtras("Some extras");
        c1.setLocation(location);
        c1.setAvailable(true);
        c1.setMatriculated(true);
        c1.setImgUrl("carpic.jpg");
        c1.setSellerId(u1.getId());
        getCarService().save(c1);

        Conversation conversation = new Conversation();
        conversation.setSenderId(u2.getId());
        conversation.setReceiverId(u1.getId());
        conversation.setTitle(c1.getManufacturer()+" "+c1.getType());

        getMessageService().newConversation(conversation);

        Assert.assertEquals(1,getMessageService().getDao().getAll().size());
    }

    @Test
    public void test_add_new_conversation_twice() throws ValidationException {
        User u1 = new User();
        u1.setUserName("conormcgregor@yahoo.com");
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
        List<User> userList = new LinkedList<>(getUserService().listAll());
        u1.setId(userList.get(0).getId());
        u2.setId(userList.get(1).getId());

        CarLocation location = new CarLocation();
        location.setLatitude(45d);
        location.setLongitude(25d);

        Car c1 = new Car();
        c1.setManufacturer("Audi");
        c1.setType("A5");
        c1.setFabricationYear(2005);
        c1.setMileAge(123456);
        c1.setPrice(5000);
        c1.setEngineType(EngineType.DIESEL);
        c1.setTransmissionType(TransmissionType.MANUAL);
        c1.setColour("blue");
        c1.setExtras("Some extras");
        c1.setLocation(location);
        c1.setAvailable(true);
        c1.setMatriculated(true);
        c1.setImgUrl("carpic.jpg");
        c1.setSellerId(u1.getId());
        getCarService().save(c1);

        Conversation conversation = new Conversation();
        conversation.setSenderId(u2.getId());
        conversation.setReceiverId(u1.getId());
        conversation.setTitle(c1.getManufacturer()+" "+c1.getType());

        getMessageService().newConversation(conversation);
        getMessageService().newConversation(conversation);
        Assert.assertEquals(1,getMessageService().getDao().getAll().size());
    }

    @Test
    public void test_verify_existing_conversation() throws ValidationException {
        User u1 = new User();
        u1.setUserName("conormcgregor@yahoo.com");
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
        List<User> userList = new LinkedList<>(getUserService().listAll());
        u1.setId(userList.get(0).getId());
        u2.setId(userList.get(1).getId());

        CarLocation location = new CarLocation();
        location.setLatitude(45d);
        location.setLongitude(25d);

        Car c1 = new Car();
        c1.setManufacturer("Audi");
        c1.setType("A5");
        c1.setFabricationYear(2005);
        c1.setMileAge(123456);
        c1.setPrice(5000);
        c1.setEngineType(EngineType.DIESEL);
        c1.setTransmissionType(TransmissionType.MANUAL);
        c1.setColour("blue");
        c1.setExtras("Some extras");
        c1.setLocation(location);
        c1.setAvailable(true);
        c1.setMatriculated(true);
        c1.setImgUrl("carpic.jpg");
        c1.setSellerId(u1.getId());
        getCarService().save(c1);

        Conversation conversation = new Conversation();
        conversation.setSenderId(u2.getId());
        conversation.setReceiverId(u1.getId());
        conversation.setTitle(c1.getManufacturer()+" "+c1.getType());

        getMessageService().newConversation(conversation);

        Assert.assertFalse(getMessageService().verifyConversation(conversation));
    }

    @Test
    public void test_get_all_conversations_by_sender() throws ValidationException {
        User u0 = new User();
        u0.setUserName("conormcgregor@gmail.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);
        getUserService().save(u0);

        User u1 = new User();
        u1.setUserName("conormcgregor@yahoo.com");
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
        List<User> userList = new LinkedList<>(getUserService().listAll());
        u0.setId(userList.get(0).getId());
        u1.setId(userList.get(1).getId());
        u2.setId(userList.get(2).getId());


        CarLocation location = new CarLocation();
        location.setLatitude(45d);
        location.setLongitude(25d);

        Car c1 = new Car();
        c1.setManufacturer("Audi");
        c1.setType("A5");
        c1.setFabricationYear(2005);
        c1.setMileAge(123456);
        c1.setPrice(5000);
        c1.setEngineType(EngineType.DIESEL);
        c1.setTransmissionType(TransmissionType.MANUAL);
        c1.setColour("blue");
        c1.setExtras("Some extras");
        c1.setLocation(location);
        c1.setAvailable(true);
        c1.setMatriculated(true);
        c1.setImgUrl("carpic.jpg");
        c1.setSellerId(u1.getId());
        getCarService().save(c1);

        Car c2 = new Car();
        c2.setManufacturer("Audi");
        c2.setType("A5");
        c2.setFabricationYear(2015);
        c2.setMileAge(32323);
        c2.setPrice(50000);
        c2.setEngineType(EngineType.DIESEL);
        c2.setTransmissionType(TransmissionType.MANUAL);
        c2.setColour("blue");
        c2.setExtras("Some extras");
        c2.setLocation(location);
        c2.setAvailable(true);
        c2.setMatriculated(true);
        c2.setImgUrl("carpic.jpg");
        c2.setSellerId(u0.getId());
        getCarService().save(c1);

        Conversation t1 = new Conversation();
        t1.setSenderId(u2.getId());
        t1.setReceiverId(u1.getId());
        t1.setTitle(c1.getManufacturer()+" "+c1.getType());

        Conversation t2 = new Conversation();
        t2.setSenderId(u2.getId());
        t2.setReceiverId(u0.getId());
        t2.setTitle(c2.getManufacturer()+" "+c2.getType());

        getMessageService().newConversation(t1);
        getMessageService().newConversation(t2);


        Assert.assertEquals(2,getMessageService().listAllConversationsBySender(u2.getId()).size());
    }

    @Test
    public void test_get_no_conversations_by_sender() throws ValidationException {

        User u1 = new User();
        u1.setUserName("conormcgregor@yahoo.com");
        u1.setPassword("password");
        u1.setPasswordValidation("password");
        u1.setFirstName("Conor");
        u1.setLastName("McGregor");
        u1.setRole(Role.SELLER);
        getUserService().save(u1);
        List<User> userList = new LinkedList<>(getUserService().listAll());

        u1.setId(userList.get(0).getId());

        Assert.assertEquals(0,getMessageService().listAllConversationsByReceiver(u1.getId()).size());
    }

    @Test
    public void test_get_no_conversations_by_receiver() throws ValidationException {

        User u2 = new User();
        u2.setUserName("pop_iosifcristian@yahoo.com");
        u2.setPassword("password");
        u2.setPasswordValidation("password");
        u2.setFirstName("Pop");
        u2.setLastName("Iosif Cristian");
        u2.setRole(Role.BUYER);
        getUserService().save(u2);
        List<User> userList = new LinkedList<>(getUserService().listAll());

        u2.setId(userList.get(0).getId());

        Assert.assertEquals(0,getMessageService().listAllConversationsBySender(u2.getId()).size());
    }

    @Test
    public void test_get_all_conversations_by_receiver() throws ValidationException {
        User u0 = new User();
        u0.setUserName("conormcgregor@gmail.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);
        getUserService().save(u0);

        User u1 = new User();
        u1.setUserName("conormcgregor@yahoo.com");
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
        List<User> userList = new LinkedList<>(getUserService().listAll());
        u0.setId(userList.get(0).getId());
        u1.setId(userList.get(1).getId());
        u2.setId(userList.get(2).getId());


        CarLocation location = new CarLocation();
        location.setLatitude(45d);
        location.setLongitude(25d);

        Car c1 = new Car();
        c1.setManufacturer("Audi");
        c1.setType("A5");
        c1.setFabricationYear(2005);
        c1.setMileAge(123456);
        c1.setPrice(5000);
        c1.setEngineType(EngineType.DIESEL);
        c1.setTransmissionType(TransmissionType.MANUAL);
        c1.setColour("blue");
        c1.setExtras("Some extras");
        c1.setLocation(location);
        c1.setAvailable(true);
        c1.setMatriculated(true);
        c1.setImgUrl("carpic.jpg");
        c1.setSellerId(u1.getId());
        getCarService().save(c1);

        Car c2 = new Car();
        c2.setManufacturer("Audi");
        c2.setType("A5");
        c2.setFabricationYear(2015);
        c2.setMileAge(32323);
        c2.setPrice(50000);
        c2.setEngineType(EngineType.DIESEL);
        c2.setTransmissionType(TransmissionType.MANUAL);
        c2.setColour("blue");
        c2.setExtras("Some extras");
        c2.setLocation(location);
        c2.setAvailable(true);
        c2.setMatriculated(true);
        c2.setImgUrl("carpic.jpg");
        c2.setSellerId(u0.getId());
        getCarService().save(c1);

        Conversation t1 = new Conversation();
        t1.setSenderId(u2.getId());
        t1.setReceiverId(u1.getId());
        t1.setTitle(c1.getManufacturer()+" "+c1.getType());

        Conversation t2 = new Conversation();
        t2.setSenderId(u2.getId());
        t2.setReceiverId(u0.getId());
        t2.setTitle(c2.getManufacturer()+" "+c2.getType());

        getMessageService().newConversation(t1);
        getMessageService().newConversation(t2);

        Assert.assertEquals(1,getMessageService().listAllConversationsByReceiver(u1.getId()).size());
    }

    @Test
    public void test_get_zero_message() throws ValidationException {
        User u0 = new User();
        u0.setUserName("conormcgregor@gmail.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);
        getUserService().save(u0);

        User u1 = new User();
        u1.setUserName("conormcgregor@yahoo.com");
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
        List<User> userList = new LinkedList<>(getUserService().listAll());
        u0.setId(userList.get(0).getId());
        u1.setId(userList.get(1).getId());
        u2.setId(userList.get(2).getId());


        CarLocation location = new CarLocation();
        location.setLatitude(45d);
        location.setLongitude(25d);

        Car c1 = new Car();
        c1.setManufacturer("Audi");
        c1.setType("A5");
        c1.setFabricationYear(2005);
        c1.setMileAge(123456);
        c1.setPrice(5000);
        c1.setEngineType(EngineType.DIESEL);
        c1.setTransmissionType(TransmissionType.MANUAL);
        c1.setColour("blue");
        c1.setExtras("Some extras");
        c1.setLocation(location);
        c1.setAvailable(true);
        c1.setMatriculated(true);
        c1.setImgUrl("carpic.jpg");
        c1.setSellerId(u1.getId());
        getCarService().save(c1);

        Car c2 = new Car();
        c2.setManufacturer("Audi");
        c2.setType("A5");
        c2.setFabricationYear(2015);
        c2.setMileAge(32323);
        c2.setPrice(50000);
        c2.setEngineType(EngineType.DIESEL);
        c2.setTransmissionType(TransmissionType.MANUAL);
        c2.setColour("blue");
        c2.setExtras("Some extras");
        c2.setLocation(location);
        c2.setAvailable(true);
        c2.setMatriculated(true);
        c2.setImgUrl("carpic.jpg");
        c2.setSellerId(u0.getId());
        getCarService().save(c1);

        Conversation t1 = new Conversation();
        t1.setSenderId(u2.getId());
        t1.setReceiverId(u1.getId());
        t1.setTitle(c1.getManufacturer()+" "+c1.getType());
        t1.setId(getMessageService().getIdByConversation(t1));

        getMessageService().newConversation(t1);

        Assert.assertEquals(0,getMessageService().listMessages(t1.getId()).size());
    }

    @Test
    public void test_get_one_message() throws ValidationException {
        User u0 = new User();
        u0.setUserName("conormcgregor@gmail.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);
        getUserService().save(u0);

        User u1 = new User();
        u1.setUserName("conormcgregor@yahoo.com");
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
        List<User> userList = new LinkedList<>(getUserService().listAll());
        u0.setId(userList.get(0).getId());
        u1.setId(userList.get(1).getId());
        u2.setId(userList.get(2).getId());


        CarLocation location = new CarLocation();
        location.setLatitude(45d);
        location.setLongitude(25d);

        Car c1 = new Car();
        c1.setManufacturer("Audi");
        c1.setType("A5");
        c1.setFabricationYear(2005);
        c1.setMileAge(123456);
        c1.setPrice(5000);
        c1.setEngineType(EngineType.DIESEL);
        c1.setTransmissionType(TransmissionType.MANUAL);
        c1.setColour("blue");
        c1.setExtras("Some extras");
        c1.setLocation(location);
        c1.setAvailable(true);
        c1.setMatriculated(true);
        c1.setImgUrl("carpic.jpg");
        c1.setSellerId(u1.getId());
        getCarService().save(c1);

        Car c2 = new Car();
        c2.setManufacturer("Audi");
        c2.setType("A5");
        c2.setFabricationYear(2015);
        c2.setMileAge(32323);
        c2.setPrice(50000);
        c2.setEngineType(EngineType.DIESEL);
        c2.setTransmissionType(TransmissionType.MANUAL);
        c2.setColour("blue");
        c2.setExtras("Some extras");
        c2.setLocation(location);
        c2.setAvailable(true);
        c2.setMatriculated(true);
        c2.setImgUrl("carpic.jpg");
        c2.setSellerId(u0.getId());
        getCarService().save(c1);

        Conversation t1 = new Conversation();
        t1.setSenderId(u2.getId());
        t1.setReceiverId(u1.getId());
        t1.setTitle(c1.getManufacturer()+" "+c1.getType());
        t1.setId(getMessageService().getIdByConversation(t1));

        getMessageService().newConversation(t1);

        Message m1 = new Message();
        m1.setSenderId(u2.getId());
        m1.setReceiverId(u1.getId());
        m1.setConversationId(t1.getId());

        getMessageService().newMessage(t1.getId(),m1);

        Assert.assertEquals(1,getMessageService().listMessages(t1.getId()).size());
    }

    @Test
    public void test_get_message_id() throws ValidationException {
        User u0 = new User();
        u0.setUserName("conormcgregor@gmail.com");
        u0.setPassword("password");
        u0.setPasswordValidation("password");
        u0.setFirstName("Conor");
        u0.setLastName("McGregor");
        u0.setRole(Role.SELLER);
        getUserService().save(u0);

        User u1 = new User();
        u1.setUserName("conormcgregor@yahoo.com");
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
        List<User> userList = new LinkedList<>(getUserService().listAll());
        u0.setId(userList.get(0).getId());
        u1.setId(userList.get(1).getId());
        u2.setId(userList.get(2).getId());


        CarLocation location = new CarLocation();
        location.setLatitude(45d);
        location.setLongitude(25d);

        Car c1 = new Car();
        c1.setManufacturer("Audi");
        c1.setType("A5");
        c1.setFabricationYear(2005);
        c1.setMileAge(123456);
        c1.setPrice(5000);
        c1.setEngineType(EngineType.DIESEL);
        c1.setTransmissionType(TransmissionType.MANUAL);
        c1.setColour("blue");
        c1.setExtras("Some extras");
        c1.setLocation(location);
        c1.setAvailable(true);
        c1.setMatriculated(true);
        c1.setImgUrl("carpic.jpg");
        c1.setSellerId(u1.getId());
        getCarService().save(c1);

        Car c2 = new Car();
        c2.setManufacturer("Audi");
        c2.setType("A5");
        c2.setFabricationYear(2015);
        c2.setMileAge(32323);
        c2.setPrice(50000);
        c2.setEngineType(EngineType.DIESEL);
        c2.setTransmissionType(TransmissionType.MANUAL);
        c2.setColour("blue");
        c2.setExtras("Some extras");
        c2.setLocation(location);
        c2.setAvailable(true);
        c2.setMatriculated(true);
        c2.setImgUrl("carpic.jpg");
        c2.setSellerId(u0.getId());
        getCarService().save(c1);

        Conversation t1 = new Conversation();
        t1.setSenderId(u2.getId());
        t1.setReceiverId(u1.getId());
        t1.setTitle(c1.getManufacturer()+" "+c1.getType());
        t1.setId(getMessageService().getIdByConversation(t1));

        getMessageService().newConversation(t1);

        Message m1 = new Message();
        m1.setSenderId(u2.getId());
        m1.setReceiverId(u1.getId());
        m1.setConversationId(t1.getId());

        getMessageService().newMessage(t1.getId(),m1);

        Assert.assertEquals(m1.getSenderId(),getMessageService().getMessageId(m1.getConversationId(),m1.getReceiverId()));
        Assert.assertEquals(m1.getReceiverId(),getMessageService().getMessageId(m1.getConversationId(),m1.getSenderId()));
    }
}
