package ro.cmm.dao.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import ro.cmm.dao.MessageDAO;
import ro.cmm.domain.Conversation;
import ro.cmm.domain.Message;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Joseph Thursday, 11.05.2017 at 15:57.
 */
public class JdbcTemplateMessageDAO implements MessageDAO {

    private JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcTemplateMessageDAO.class);

    public JdbcTemplateMessageDAO(DataSource dataSource){
        this.jdbcTemplate=new JdbcTemplate(dataSource);
    }

    @Override
    public void newConversation(Conversation conversation) {
//        LOGGER.info("Trying to create a new conversation between users: "+conversation.getSenderName()+" and "+conversation.getReceiverName());
    String sql = "INSERT INTO conversations (sender_id, receiver_id, title, sender_name, receiver_name, last_message)"
            +"VALUES ( ?,"+
                     " ?,"+
                     " ?,"+
                     " ?,"+
                     " ?,"+
                     " ?"+
                     " ) returning id ";
    Long newId = jdbcTemplate.queryForObject(sql, new Object[]{
            conversation.getSenderId(),
            conversation.getReceiverId(),
            conversation.getTitle(),
            conversation.getSenderName(),
            conversation.getReceiverName(),
            conversation.getLastMessage()
    }, new RowMapper<Long>() {
        @Override
        public Long mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getLong(1);
        }
    });
    conversation.setId(newId);
    }

    @Override
    public boolean verifyConversation(Conversation conversation) {
//        LOGGER.info("Verifying if there is a conversation between users: "+conversation.getSenderName()+" and "+conversation.getReceiverName()+" with title: "+conversation.getTitle());
        String query="SELECT * FROM conversations ";
        Collection<Conversation> conversations = jdbcTemplate.query(query,new ConversationResultSetExtractor());
        if (conversations.size()==0){
//            LOGGER.info("Here is no conversation");
            return true;
        }else {
            for (Conversation c : conversations) {
                if (c.equals(conversation)) {
//                    LOGGER.info("Here is already a conversation");
                    return false;
                }
            }
        }
//        LOGGER.info("Here is no conversation");
        return true;
    }

    @Override
    public void newMessage(long id, Message message) {
//        LOGGER.info("Trying to create a new message betwen users: "+message.getSenderId()+" and "+message.getReceiverId());
    String sql = "INSERT INTO messages (conversation_id, sender_id, receiver_id, message, time) "+
            "VALUES (?, ?, ?, ?, ?)";
    jdbcTemplate.update(sql,
            message.getConversationId(),
            message.getSenderId(),
            message.getReceiverId(),
            message.getMessage(),
            message.getTime());
    Conversation c= getById(id);
    c.setLastMessage(message.getTime());
    updateLastMessage(c);
    }

    public void updateLastMessage(Conversation conversation){
        String sql = "UPDATE conversations SET last_message=? WHERE id=? ";
        jdbcTemplate.update(sql, conversation.getLastMessage(),conversation.getId());
    }

    @Override
    public Collection<Conversation> getAllConversationsBySender(long id) {
//        LOGGER.info("Getting all conversations under sender id: "+id);
        String query="SELECT * FROM conversations WHERE sender_id=?";
        Collection<Conversation> conversations = jdbcTemplate.query(query,new ConversationResultSetExtractor(),id);
        return conversations;
    }

    @Override
    public Collection<Conversation> getAllConversationsByReceiver(long id) {
//        LOGGER.info("Getting all conversations under receiver id: "+id);
        String query="SELECT * FROM conversations WHERE receiver_id=?";
        Collection<Conversation> conversations = jdbcTemplate.query(query,new ConversationResultSetExtractor(),id);
        return conversations;
    }

    @Override
    public Collection<Conversation> getAllConversations(long id){
        String query="SELECT * FROM conversations WHERE receiver_id=? or sender_id=?";
        Collection<Conversation> conversations = jdbcTemplate.query(query,new ConversationResultSetExtractor(),id,id);
        return conversations;
    }

    @Override
    public long getIdByConversation(Conversation conversation) {
//        LOGGER.info("Trying to get id of this conversation: "+conversation.toString());
        String query="SELECT * FROM conversations WHERE sender_id=? AND receiver_id=? AND title=?";
        Collection<Conversation> conversations = jdbcTemplate.query(query,new ConversationResultSetExtractor(),
                conversation.getSenderId(),conversation.getReceiverId(),conversation.getTitle());
    if (conversations.size()==1){
        Conversation c = conversations.iterator().next();
//        LOGGER.info("Got this id: "+c.getId());
        return c.getId();
        }
//        LOGGER.info("Found no conversation/no id");
    return 0;
    }

    @Override
    public List<Message> getMessages(long id) {
//        LOGGER.info("Trying to get all messages of conversation with id: "+id);
        String query="SELECT * FROM messages WHERE conversation_id=?";
        List<Message> messages = jdbcTemplate.query(query,new MessageResultSetExtractor(),id);
        return messages;
    }

    @Override
    public Conversation getById(long id) {
//        LOGGER.info("Trying to get conversation with this id: "+id);
        String query="SELECT * FROM conversations WHERE id=?";
        Collection<Conversation> conversations = jdbcTemplate.query(query,new ConversationResultSetExtractor(),id);
        if (conversations.size()==1){
            Conversation c = conversations.iterator().next();
//            LOGGER.info("Found a conversation");
            return c;
        }else{
//            LOGGER.info("Found no conversation");
            return null;
        }
    }

    @Override
    public Collection<List<Message>> getAll() {
        return null;
    }

    @Override
    public void clearMemory() {

    }

    private static class ConversationResultSetExtractor implements ResultSetExtractor<Collection<Conversation>>{
        @Override
        public Collection<Conversation> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            Collection<Conversation> conversations = new LinkedList();
            while (resultSet.next()){
                Conversation c = new Conversation();
                c.setId(resultSet.getLong("id"));
                c.setSenderId(resultSet.getLong("sender_id"));
                c.setReceiverId(resultSet.getLong("receiver_id"));
                c.setTitle(resultSet.getString("title"));
                c.setSenderName(resultSet.getString("sender_name"));
                c.setReceiverName(resultSet.getString("receiver_name"));
                c.setLastMessage(resultSet.getString("last_message"));
                conversations.add(c);

            }
            return conversations;
        }
    }

    private static class MessageResultSetExtractor implements  ResultSetExtractor<List<Message>>{
        @Override
        public List<Message> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<Message> messages = new LinkedList();
            while(resultSet.next()){
                Message m = new Message();
                m.setConversationId(resultSet.getLong("conversation_id"));
                m.setSenderId(resultSet.getLong("sender_id"));
                m.setReceiverId(resultSet.getLong("receiver_id"));
                m.setMessage(resultSet.getString("message"));
                m.setTime(resultSet.getString("time"));
                messages.add(m);
            }
            return messages;
        }
    }
}
