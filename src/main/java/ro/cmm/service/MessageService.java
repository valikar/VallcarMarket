package ro.cmm.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.cmm.dao.inmemory.IMMessageDAO;
import ro.cmm.domain.Conversation;
import ro.cmm.domain.Message;

import java.util.*;


/**
 * Created by Joseph Friday, 05.05.2017 at 11:59.
 */
@Service
public class MessageService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private IMMessageDAO dao;

    public void newMessage(long id, Message message) {
        LOGGER.debug("Creating new message to conversation with id: "+id);
            dao.newMessage(id,message);
    }

    public void newConversation(Conversation conversation){
        dao.verifyConversation(conversation);
        if (dao.verifyConversation(conversation)){
            LOGGER.debug("Creating new conversation between this users: "+conversation.getSenderId()+" , "+conversation.getReceiverId());
            dao.newConversation(conversation);
        }
    }

    public boolean verifyConversation(Conversation conversation){
        LOGGER.debug("Verifying if this conversation with this sender: "+conversation.getSenderId()
        +" and this receiver: "+conversation.getReceiverId()+" with this title: "+conversation.getTitle()
        +"is not in our db/memory");
        if (dao.verifyConversation(conversation)){
            LOGGER.debug("This conversation is not in our db/meomory");
            return true;
        }
        LOGGER.debug("This conversation is in our db/meomory");
        return false;
    }

    public Collection<Conversation> listAllConversationsBySender(long id){
        LOGGER.debug("Getting all conversations with senderId: "+id);
        return dao.getAllConversationsBySender(id);
    }

    public Collection<Conversation> listAllConversationsByReceiver(long id){
        LOGGER.debug("Getting all conversations with receiverId: "+id);
        return dao.getAllConversationsByReceiver(id);
    }

    public Collection<Message> listMessages(long id){
        LOGGER.debug("Getting all messages of conversation with Id: "+id);
        return dao.getMessages(id);
    }

    public Conversation getById(long id){
        LOGGER.debug("Getting conversation with id: "+id);
        return dao.getById(id);
    }

    public long getMessageId(long conversationId, long id){
        LOGGER.debug("Getting other message id for this conversation: "+conversationId+
        "with this initial id: "+id);
    Conversation conversation = getById(conversationId);
    if (conversation.getSenderId()==id)
        return conversation.getReceiverId();
    else
        return conversation.getSenderId();
    }

    public long getIdByConversation(Conversation conversation){
      LOGGER.debug("Getting id for conversation with this sender: "+conversation.getSenderId()
              +" and this receiver: "+conversation.getReceiverId()+" with this title: "+conversation.getTitle());
        return dao.getIdByConversation(conversation);
    }

    public Collection<List<Message>> getAll(){
       return dao.getAll();
    }

    public void setDao(IMMessageDAO dao) {
        this.dao = dao;
    }

    public IMMessageDAO getDao() {
        return dao;
    }
}
