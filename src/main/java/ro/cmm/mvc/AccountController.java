package ro.cmm.mvc;

import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private MessageService messageService;

    @RequestMapping("/seller")
    public ModelAndView seller() {
        ModelAndView modelAndView = new ModelAndView("/user/seller");
        return modelAndView;
    }

    @RequestMapping("/buyer")
    public ModelAndView buyer() {
        ModelAndView modelAndView = new ModelAndView("/user/buyer");
        return modelAndView;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(long id) {
        User user = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView("/user/edit");
        modelAndView.addObject("user", user);
        return modelAndView;
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
            if (carService.getById(id).getSellerId()!=loginService.getDao().getId()) {
                carService.countViews(id);
            }
            modelAndView.addObject("car", car);
        }
        return modelAndView;
    }


    @RequestMapping("/list/car/checkIn")
    public String checkIn(long id) throws ValidationException {
        Car car = carService.getById(id);

        if (!car.isAvailable()) {
            car.setAvailable(true);
            car.setLocation(carService.generateRandomLocationOnCarSave());
        }else {
            car.setAvailable(false);
        }
        carService.save(car);
        return "redirect:/account/list?id="+Long.toString(car.getSellerId());
    }

    @RequestMapping("/list")
    public ModelAndView list(long id){
        ModelAndView modelAndView = new ModelAndView("/car/list");
        if (carService.getCarListOfSeller(id)!=null){
            Collection<Car> cars = carService.getCarListOfSeller(id);
            modelAndView.addObject("cars",cars);
        }else {
            modelAndView.addObject(new RedirectView("/account/list?id="+Long.toString(loginService.getDao().getId())));
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
                cars.add(carService.getById(l));
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
        userService.deleteBookmark(id);
        return "redirect:/account/bookmark/list?id="+Long.toString(loginService.getDao().getId());
    }

    @RequestMapping("/bookmark")
    public String bookmark(long id){
    userService.addBookmark(id);
    return "redirect:/account/list/car?id="+Long.toString(id);
    }


    @RequestMapping("/message/list")
    public ModelAndView conversations(long id){
        ModelAndView modelAndView = new ModelAndView("/message/list");
        if (loginService.getDao().getRole().equals(Role.SELLER)) {
            if (messageService.listAllConversationsByReceiver(id) != null) {
                Collection<Conversation> conversations = messageService.listAllConversationsByReceiver(id);
                modelAndView.addObject("conversations", conversations);
            } else {
                Collection<Conversation> conversations = new LinkedList<>();
                modelAndView.addObject("conversations", conversations);
            }
        }else{
            if (messageService.listAllConversationsBySender(id)!=null){
                Collection<Conversation> conversations = messageService.listAllConversationsBySender(id);
                modelAndView.addObject("conversations",conversations);
            }else {
                Collection<Conversation> conversations = new LinkedList<>();
                modelAndView.addObject("conversations",conversations);
            }
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
        conversation.setSenderId(loginService.getDao().getId());
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
            System.out.println(conversation.getId());
            System.out.println(message.getConversationId());
        }else{
            conversation.setId(messageService.getIdByConversation(conversation));
            System.out.println(conversation.getId());
            RedirectView redirectView = new RedirectView("/account/message/list/conversation?id=" + Long.toString(conversation.getId()));
            modelAndView.setView(redirectView);
        }
        return modelAndView;
    }

    @RequestMapping("/message/list/conversation/reply")
    public String reply(long id,@ModelAttribute("message") Message message){
        message.setConversationId(id);
        message.setReceiverId(messageService.getMessageId(id,loginService.getDao().getId()));
        message.setSenderId(loginService.getDao().getId());
        messageService.newMessage(id,message);
        return "redirect:/account/message/list/conversation?id="+Long.toString(id);
    }
}
