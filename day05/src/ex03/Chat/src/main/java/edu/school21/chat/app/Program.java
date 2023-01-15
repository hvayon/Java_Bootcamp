package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessageRepository;
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
        MessageRepository messageRepository = new MessagesRepositoryJdbcImpl(makeHikariDataSource());
        Message copy = null;

        try {
            Optional<Message> messageOptional = messageRepository.findById(3L);


            if (messageOptional.isPresent()) {
                copy = new Message(messageOptional.get().getMessageAuthor().getUserId(),
                        messageOptional.get().getMessageAuthor(),
                        messageOptional.get().getMessageRoom(),
                        messageOptional.get().getMessageText(),
                        messageOptional.get().getData());
            }
            Message message = messageOptional.get();
            message.setMessage("Всем пока");
            message.setData(null);
            messageRepository.update(message);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        System.out.println((messageRepository.findById(3L)));
    }
}
