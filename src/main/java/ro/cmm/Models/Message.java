package ro.cmm.Models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Message {

    private long conversationId;

    private long senderId;
    private long receiverId;

    private String message;

    private String time;

    DateFormat df = new SimpleDateFormat("HH:mm");
    Date dateobj = new Date();

    public Message() {
        this.time = df.format(dateobj);
        this.message="";
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime() {
        this.time = df.format(dateobj);    }

    public void setTime(String time){
        this.time = time;
    }

    public long getConversationId() {
        return conversationId;
    }

    public void setConversationId(long conversationId) {
        this.conversationId = conversationId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "conversationId=" + conversationId +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", message='" + message + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
