package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private Long userId;
    private String login;
    private String password;
    private List<Chatroom> createdRooms;
    private List<Chatroom> joinedRooms;

    public User(Long userId, String login, String password, List<Chatroom> createdRooms, List<Chatroom> joinedRooms) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.joinedRooms = joinedRooms;
    }

    public User(String login, String password, List<Chatroom> createdRooms, List<Chatroom> joinedRooms) {
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.joinedRooms = joinedRooms;
    }

    public Long getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Chatroom> getJoinedRooms() {
        return joinedRooms;
    }

    public List<Chatroom> getCreatedRooms() {
        return createdRooms;
    }

    public void setId(Long userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return userId == user.userId && login.equals(user.login) && password.equals(user.password)
                && createdRooms.equals(user.createdRooms) && joinedRooms.equals(user.joinedRooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, password, createdRooms, joinedRooms);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password=" + password +
                ", createdRooms=" + createdRooms +
                ", joinedRooms=" + joinedRooms +
                '}';
    }
}
