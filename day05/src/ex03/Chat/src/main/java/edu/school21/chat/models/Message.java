package edu.school21.chat.models;

import java.util.Objects;
import java.time.LocalDateTime;

public class Message {
    private Long messageId;
    private User messageAuthor;
    private Chatroom messageRoom;
    private String messageText;
    private LocalDateTime messageDate;

    public Message(Long messageId, User messageAuthor, Chatroom messageRoom, String messageText, LocalDateTime messageDate) {
        this.messageId = messageId;
        this.messageAuthor = messageAuthor;
        this.messageRoom = messageRoom;
        this.messageText = messageText;
        this.messageDate = messageDate;

    }

    public Message(User messageAuthor, Chatroom messageRoom, String messageText, LocalDateTime messageDate) {
        this.messageAuthor = messageAuthor;
        this.messageRoom = messageRoom;
        this.messageText = messageText;
        this.messageDate = messageDate;
    }

    public Long getMessageId() {
        return messageId;
    }

    public User getMessageAuthor() {
        return messageAuthor;
    }

    public Chatroom getMessageRoom() {
        return messageRoom;
    }

    public String getMessageText() {
        return messageText;
    }

    public LocalDateTime getData() {
        return messageDate;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public void setMessageAuto(User messageAuthor) {
        this.messageAuthor = messageAuthor;
    }

    public void setMessageRoom(Chatroom messageRoom) {
        this.messageRoom = messageRoom;
    }

    public void setMessage(String messageText) {
        this.messageText = messageText;
    }

    public void setData(LocalDateTime messageDate) {
        this.messageDate = messageDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return messageId == message.messageId && messageAuthor.equals(message.messageAuthor) && messageRoom.equals(message.messageRoom) && messageText.equals(message.messageText) && messageDate.equals(message.messageDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, messageAuthor, messageRoom, messageText, messageDate);
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", messageAuthor=" + messageAuthor +
                ", messageRoom=" + messageRoom +
                ", messageText='" + messageText + '\'' +
                ", messageDate=" + messageDate +
                '}';
    }
}