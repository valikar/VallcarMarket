package ro.cmm.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ro.cmm.domain.*;
import ro.cmm.service.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Joseph Monday, 24.04.2017 at 20:51.
 */
@Controller
@RequestMapping("/account")
public class AccountController {

//    private User getCurrentUser() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(auth.getAuthorities());
//        String name = auth.getName(); //get logged in username
//        User currentUser = userService.searchByUsername(name);
//        return currentUser;
//    }


    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping("/seller")
    public ModelAndView seller() {
        ModelAndView modelAndView = new ModelAndView("/user/seller");
        modelAndView.addObject("currentUser", securityService.getCurrentUser());
        return modelAndView;
    }

    @RequestMapping("/buyer")
    public ModelAndView buyer() {
        ModelAndView modelAndView = new ModelAndView("/user/buyer");
        return modelAndView;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(long id) {
        User currentUser = securityService.getCurrentUser();
        if(currentUser.getId() != id) {
            throw new AccessDeniedException("You are not authorized to edit this account.");
        } else {
            ModelAndView modelAndView = new ModelAndView("/user/edit");
            modelAndView.addObject("user", currentUser);
            return modelAndView;
        }
    }
    @RequestMapping("/save")
    public ModelAndView save(
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        boolean hasErrors = false;

        if (!bindingResult.hasErrors()) {
            try {
                userService.save(user);
                RedirectView redirectView = new RedirectView("/");
                modelAndView.setView(redirectView);
            }catch (ValidationException e) {
                for (String msg : e.getCauses()) {
                    bindingResult.addError(new ObjectError("userLogin", msg));
                }
                hasErrors = true;
            }
        } else {
            hasErrors = true;
        }
        if (hasErrors){
            modelAndView = new ModelAndView("user/signup");
            modelAndView.addObject("errors", bindingResult.getAllErrors());
            modelAndView.addObject("user", user);
        }
        return modelAndView;
    }

    @RequestMapping("/list/car")
    public ModelAndView display(long id) {
        ModelAndView modelAndView = new ModelAndView("/car/display");
        if (carService.getById(id)!=null) {
            Car car = carService.getById(id);
            if (securityService.getCurrentUser()!=null) {
                if (carService.getById(id).getSellerId() != securityService.getCurrentUser().getId()) {
                    carService.countViews(id);
                }
            }
            modelAndView.addObject("car", car);
        }
        return modelAndView;
    }


    @RequestMapping("/list/car/checkIn")
    public String checkIn(long id) throws ValidationException {
        Car car = carService.getById(id);
        if (securityService.getCurrentUser().getId() != (car.getSellerId())) {
            throw new AccessDeniedException("You are not authorized to access this resource");
        } else {
            if (!car.getAvailable()) {
                car.setAvailable(true);
                car.setLocation(carService.generateRandomLocationOnCarSave());
                carService.getDao().update(car);
            } else {
                car.setAvailable(false);
                CarLocation carLocation = new CarLocation();
                carLocation.setLatitude(null);
                carLocation.setLongitude(null);
                car.setLocation(carLocation);
                carService.getDao().update(car);
            }
            //carService.save(car);
            return "redirect:/account/list?id=" + Long.toString(car.getSellerId());
        }
    }

    @RequestMapping("/list")
    public ModelAndView list(long id){
        ModelAndView modelAndView = new ModelAndView("/car/list");
        if (carService.getCarListOfSeller(id)!=null){
            Collection<Car> cars = carService.getCarListOfSeller(id);
            modelAndView.addObject("cars",cars);
        }else {
            modelAndView.addObject(new RedirectView("/account/list?id="+Long.toString(securityService.getCurrentUser().getId())));
        }
        return modelAndView;
    }

    @RequestMapping("/bookmark/list")
    public ModelAndView bookmarks(long id){
        ModelAndView modelAndView = new ModelAndView("/car/bookmarks");
        if (userService.getBookmarks(id)!=null){
            Collection<Long> bookmarks = userService.getBookmarks(id);
            Collection<Car> cars = new LinkedList<>();
            for (Long l : bookmarks){
                Car car = carService.getById(l);
                car.setDistance(5+(int)(Math.random()*((250-5)+1)));
                cars.add(car);
            }
            modelAndView.addObject("cars",cars);
        }else {
            Collection<Car> cars = new LinkedList<>();
            modelAndView.addObject("cars",cars);
            modelAndView.addObject(new RedirectView("/"));
        }
        return modelAndView;
    }

    @RequestMapping("/bookmark/delete")
    public String deleteBookmark(long id){
        userService.deleteBookmark(id,securityService.getCurrentUser().getId());
        return "redirect:/account/bookmark/list?id="+Long.toString(securityService.getCurrentUser().getId());
    }

    @RequestMapping("/bookmark")
    public String bookmark(long id){
    userService.addBookmark(id,securityService.getCurrentUser().getId());
    return "redirect:/account/list/car?id="+Long.toString(id);
    }


    @RequestMapping("/message/list")
    public ModelAndView conversations(long id){
        ModelAndView modelAndView = new ModelAndView("/message/list");
            if (messageService.getAllConversations(id) != null) {

                Collection<Conversation> conversations =messageService.getAllConversations(id);
                modelAndView.addObject("conversations", conversations);
            } else {
                Collection<Conversation> conversations = new LinkedList<>();
                modelAndView.addObject("conversations", conversations);
            }
        return modelAndView;
    }

    @RequestMapping("/message/list/conversation")
    public ModelAndView conversation (long id){
    ModelAndView modelAndView = new ModelAndView("/message/conversation");

    Conversation conversation = messageService.getById(id);
    Message message = new Message();

    message.setConversationId(id);
    message.setSenderId(conversation.getSenderId());
    message.setReceiverId(conversation.getReceiverId());

    modelAndView.addObject("conversation", conversation);
    modelAndView.addObject("message", message);

    Collection<Message> messages =messageService.listMessages(id);
    modelAndView.addObject("messages",messages);

    return modelAndView;
    }


    @RequestMapping("/list/car/conversation")
    public ModelAndView newConversation(long id){
        ModelAndView modelAndView = new ModelAndView();

        Conversation conversation = new Conversation();
        Message message = new Message();

        conversation.setReceiverId(carService.getById(id).getSellerId());
        conversation.setSenderId(securityService.getCurrentUser().getId());
        conversation.setTitle(carService.getById(id).getManufacturer()+" "+carService.getById(id).getType());
        conversation.setSenderName(userService.getById(conversation.getSenderId()).getFirstName()+
                " "+userService.getById(conversation.getSenderId()).getLastName());
        conversation.setReceiverName(userService.getById(conversation.getReceiverId()).getFirstName()+
                " "+userService.getById(conversation.getReceiverId()).getLastName());

        if (messageService.verifyConversation(conversation)) {
             modelAndView.setViewName("/message/new");
            messageService.newConversation(conversation);
            message.setConversationId(conversation.getId());
            message.setSenderId(conversation.getSenderId());
            message.setReceiverId(conversation.getReceiverId());
            modelAndView.addObject("conversation", conversation);
            modelAndView.addObject("message", message);
        }else{
            conversation.setId(messageService.getIdByConversation(conversation));
            RedirectView redirectView = new RedirectView("/account/message/list/conversation?id=" + Long.toString(conversation.getId()));
            modelAndView.setView(redirectView);
        }
        return modelAndView;
    }

    @RequestMapping("/message/list/conversation/reply")
    public String reply(long id,@ModelAttribute("message") Message message){
        message.setConversationId(id);
        message.setReceiverId(messageService.getMessageId(id,securityService.getCurrentUser().getId()));
        message.setSenderId(securityService.getCurrentUser().getId());
        messageService.newMessage(id,message);
        messageService.getById(id).setLastMessage(message.getTime());
        return "redirect:/account/message/list/conversation?id="+Long.toString(id);
    }
}
