package ro.cmm.dao;

import ro.cmm.Models.Conversation;
import ro.cmm.Models.Message;

import java.util.Collection;
import java.util.List;


public interface MessageDAO {

    void newConversation(Conversation conversation);

    boolean verifyConversation(Conversation conversation);

    void newMessage(long id,Message message);

    Collection<Conversation> getAllConversationsBySender(long id);

    Collection<Conversation> getAllConversationsByReceiver(long id);

    Collection<Conversation> getAllConversations(long id);

    long getIdByConversation(Conversation conversation);

    List<Message> getMessages(long id);

    Conversation getById(long id);

    Collection<List<Message>> getAll();

    void clearMemory();
}
