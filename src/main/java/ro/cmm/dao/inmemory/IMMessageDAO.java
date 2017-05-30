package ro.cmm.dao.inmemory;

import org.springframework.stereotype.Component;
import ro.cmm.dao.MessageDAO;
import ro.cmm.domain.Conversation;
import ro.cmm.domain.Message;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Joseph Friday, 05.05.2017 at 14:16.
 */
public class IMMessageDAO implements MessageDAO {

    private static AtomicLong ID = new AtomicLong(System.currentTimeMillis());

    private Map<Conversation,List<Message>> messagesList = new HashMap<>();

    @Override
    public void newConversation(Conversation conversation) {
        List<Message> messages = new LinkedList<>();
        conversation.setId(ID.getAndIncrement());
        messagesList.put(conversation, messages);
    }

    @Override
    public void newMessage(long id,Message message) {
        List<Message> updatedMessagesList = messagesList.get(getById(id));
        updatedMessagesList.add(message);
        messagesList.replace(getById(id),updatedMessagesList);
    }

    @Override
    public boolean verifyConversation(Conversation conversation) {
        for (Conversation c :messagesList.keySet()){
            if (c.equals(conversation))
                return false;
        }
        return true;
    }

    @Override
    public Collection<Conversation> getAllConversationsBySender(long id) {
    Collection<Conversation> messages = new LinkedList<>();
        for (Conversation conversation : messagesList.keySet()){
            if (conversation.getSenderId()==id){
                messages.add(conversation);
            }
        }
        return messages;
    }

    @Override
    public Collection<Conversation> getAllConversationsByReceiver(long id) {
        Collection<Conversation> messages = new LinkedList<>();
        for (Conversation conversation : messagesList.keySet()){
            if (conversation.getReceiverId()==id){
                messages.add(conversation);
            }
        }
        return messages;
    }

    @Override
    public Collection<Conversation> getAllConversations(long id) {
        Collection<Conversation> messages = new LinkedList<>();
        for (Conversation conversation : messagesList.keySet()){
            if (conversation.getReceiverId()==id||conversation.getSenderId()==id){
                messages.add(conversation);
            }
        }
        return messages;    }

    @Override
    public List<Message> getMessages(long id) {
        List<Message> messages = new LinkedList<>();
        for (Conversation conversation : messagesList.keySet()) {
            if (conversation.getId() == id) {
                messages= messagesList.get(conversation);
            }
        }
        return messages;
    }

    @Override
    public Conversation getById(long id) {

        for (Conversation conversation : messagesList.keySet()) {
            if (conversation.getId() == id) {
                return conversation;
            }
        }
            return null;
    }

    @Override
    public long getIdByConversation(Conversation conversation) {
        for (Conversation c : messagesList.keySet()){
            if (c.equals(conversation)){
                return c.getId();
            }
        }
        return 0;
    }

    public Collection<List<Message>> getAll() {
        return messagesList.values();
    }

    public void clearMemory(){
        messagesList.clear();
    }
}
