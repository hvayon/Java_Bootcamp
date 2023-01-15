package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Chatroom {
    private int chatroomId;
    private String name;
    private User owner;
    private List<Message> messagesInRoom;

    public Chatroom(int id, String name, User owner, List<Message> messagesInRoom) {
        this.chatroomId = id;
        this.name = name;
        this.owner = owner;
        this.messagesInRoom = messagesInRoom;
    }

    public int getChatroomId() {
        return chatroomId;
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

    public List<Message> getMessagesInRoom() {
        return messagesInRoom;
    }

    public void setChatroomId(int chatroomId) {
        this.chatroomId = chatroomId;
    }

    public void seеName(String roomName) {
        this.name = name;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setMessagesInRoom(List<Message> messagesInRoom) {
        this.messagesInRoom = messagesInRoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chatroom chatroom = (Chatroom) o;
        return chatroomId == chatroom.chatroomId && name.equals(chatroom.name) && owner.equals(chatroom.owner) && messagesInRoom.equals(chatroom.messagesInRoom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatroomId, name, owner, messagesInRoom);
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "chatroomId=" + chatroomId +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", messagesInRoom=" + messagesInRoom +
                '}';
    }
}