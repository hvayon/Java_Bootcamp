package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import javax.sql.DataSource;

public class Program {
    public Program() {
    }

    public static DataSource makeHikariDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        hikariDataSource.setUsername("postgres");
        hikariDataSource.setPassword("");
        return hikariDataSource;
    }

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        MessagesRepositoryJdbcImpl messagesRepositoryJdbc = new MessagesRepositoryJdbcImpl(makeHikariDataSource());

        User user = new User(
                1L,
                "Alex",
                "sslowpok",
                new ArrayList<Chatroom>(),
                new ArrayList<Chatroom>()
        );
        Chatroom chatroom = new Chatroom(
                1L,
                "boltalka",
                user,
                new ArrayList<Message>()
        );

        Message message = new Message(
                null,
                user,
                chatroom,
                "Кто будет мандарины?",
                LocalDateTime.now()
        );

        messagesRepositoryJdbc.save(message);

        System.out.println("Message saved: id = " + message.getMessageId());
    }
}
