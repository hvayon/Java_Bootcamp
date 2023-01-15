package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import java.sql.SQLException;
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MessagesRepositoryJdbcImpl messagesRepositoryJdbc = new MessagesRepositoryJdbcImpl(makeHikariDataSource());

        try {
            System.out.println("Введите ID сообщения:");
            Optional<Message> message = messagesRepositoryJdbc.findById(scanner.nextLong());
            if (message.isPresent()) {
                System.out.println(message.get());
            } else {
                System.err.println("Сообщение не найдено!");
            }

        } catch (SQLException var5) {
            System.err.println("Сообщение не найдено!");
        }
    }
}
