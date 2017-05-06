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
            dao.newMessage(id,message);
    }

    public void newConversation(Conversation conversation){
        if (dao.verifyConversation(conversation)){
            dao.newConversation(conversation);
        }
    }

    public boolean verifyConversation(Conversation conversation){
        if (dao.verifyConversation(conversation)){
            return true;
        }
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
        return dao.getById(id);
    }

    public long getMessageId(long conversationId, long otherId){
    Conversation conversation = getById(conversationId);
    if (conversation.getSenderId()==otherId)
        return conversation.getReceiverId();
    else
        return conversation.getSenderId();
    }

    public long getIdByConversation(Conversation conversation){
      return dao.getIdByConversation(conversation);
    }


}
