package ro.cmm.domain;

/**
 * Created by Joseph Friday, 05.05.2017 at 11:54.
 */
public class Conversation extends AbstractModel{

    private long senderId;
    private long receiverId;

    private String title;

    private String senderName;
    private String receiverName;

    private String lastMessage;


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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conversation that = (Conversation) o;

        if (senderId != that.senderId) return false;
        if (receiverId != that.receiverId) return false;
        return title != null ? title.equals(that.title) : that.title == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (senderId ^ (senderId >>> 32));
        result = 31 * result + (int) (receiverId ^ (receiverId >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", title='" + title + '\'' +
                '}';
    }
}
