package ro.cmm.dao.db;

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

    public JdbcTemplateMessageDAO(DataSource dataSource){
        this.jdbcTemplate=new JdbcTemplate(dataSource);
    }

    @Override
    public void newConversation(Conversation conversation) {
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
        String query="SELECT * FROM conversations";
        Collection<Conversation> conversations = jdbcTemplate.query(query,new ConversationResultSetExtractor());
        for (Conversation c : conversations){
            if (c.equals(conversation))
                return false;
        }
        return true;
    }

    @Override
    public void newMessage(long id, Message message) {
    String sql = "INSERT INTO messages (conversation_id, sender_id, receiver_id, message, time) "+
            "VALUES (?, ?, ?, ?, ?)";
    jdbcTemplate.update(sql,
            message.getConversationId(),
            message.getSenderId(),
            message.getReceiverId(),
            message.getMessage(),
            message.getTime());
    }

    @Override
    public Collection<Conversation> getAllConversationsBySender(long id) {
        String query="SELECT * FROM conversations WHERE sender_id=?";
        Collection<Conversation> conversations = jdbcTemplate.query(query,new ConversationResultSetExtractor(),id);
        return conversations;
    }

    @Override
    public Collection<Conversation> getAllConversationsByReceiver(long id) {
        String query="SELECT * FROM conversations WHERE receiver_id=?";
        Collection<Conversation> conversations = jdbcTemplate.query(query,new ConversationResultSetExtractor(),id);
        return conversations;
    }

    @Override
    public long getIdByConversation(Conversation conversation) {
        String query="SELECT * FROM conversations WHERE sender_id=? AND receiver_id=? AND title=?";
        Collection<Conversation> conversations = jdbcTemplate.query(query,new ConversationResultSetExtractor(),
                conversation.getSenderId(),conversation.getReceiverId(),conversation.getTitle());
    if (conversations.size()==1){
        Conversation c = conversations.iterator().next();
        return c.getId();
        }
    return 0;
    }

    @Override
    public List<Message> getMessages(long id) {
        String query="SELECT * FROM messages WHERE conversation_id=?";
        List<Message> messages = jdbcTemplate.query(query,new MessageResultSetExtractor(),id);
        return messages;
    }

    @Override
    public Conversation getById(long id) {
        String query="SELECT * FROM conversations WHERE id=?";
        Collection<Conversation> conversations = jdbcTemplate.query(query,new ConversationResultSetExtractor(),id);
        if (conversations.size()==1){
            Conversation c = conversations.iterator().next();
            return c;
        }else{
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
            Map<Long,Conversation> conversations = new HashMap<>();
            long id = resultSet.getLong("id");
            while (resultSet.next()){
            if (!conversations.keySet().contains(id)){
                Conversation c = new Conversation();
                c.setId(id);
                c.setSenderId(resultSet.getLong("sender_id"));
                c.setReceiverId(resultSet.getLong("receiver_id"));
                c.setTitle(resultSet.getString("title"));
                c.setSenderName(resultSet.getString("sender_name"));
                c.setReceiverName(resultSet.getString("receiver_name"));
                c.setLastMessage(resultSet.getString("last_message"));
                conversations.put(id,c);
            }
            }
            return conversations.values();
        }
    }

    private static class MessageResultSetExtractor implements  ResultSetExtractor<List<Message>>{
        @Override
        public List<Message> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<Message> messages = new LinkedList<>();
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
