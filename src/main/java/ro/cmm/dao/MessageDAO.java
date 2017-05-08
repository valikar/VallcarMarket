package ro.cmm.dao;

import ro.cmm.domain.Conversation;
import ro.cmm.domain.Message;

import java.util.Collection;
import java.util.List;

/**
 * Created by Joseph Friday, 05.05.2017 at 14:18.
 */
public interface MessageDAO {

    void newConversation(Conversation conversation);

    boolean verifyConversation(Conversation conversation);

    void newMessage(long id,Message message);

    Collection<Conversation> getAllConversationsBySender(long id);

    Collection<Conversation> getAllConversationsByReceiver(long id);

    long getIdByConversation(Conversation conversation);

    List<Message> getMessages(long id);

    Conversation getById(long id);

    Collection<List<Message>> getAll();

}
