package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessageRepository {
    private final DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();;

        String queryMessage = "select * from chat.message where id=?";
        PreparedStatement statementMessage = connection.prepareStatement(queryMessage);
        statementMessage.setLong(1, id);
        statementMessage.execute();
        ResultSet resultSetMessage = statementMessage.getResultSet();
        resultSetMessage.next();

        String queryUser = "select * from chat.user where id=?";
        PreparedStatement statementUser = connection.prepareStatement(queryUser);
        statementUser.setLong(1, id);
        statementUser.execute();
        ResultSet resultSetUser = statementUser.getResultSet();
        resultSetUser.next();

        String queryRoom = "select * from chat.room where id= ?";
        PreparedStatement statementRoom = connection.prepareStatement(queryRoom);
        statementRoom.setLong(1, id);
        statementRoom.execute();
        ResultSet resultSetRoom = statementRoom.getResultSet();
        resultSetRoom.next();

        User user = new User(
                resultSetUser.getLong("id"),
                resultSetUser.getString("login"),
                resultSetUser.getString("password"),
                new ArrayList<Chatroom>(),
                new ArrayList<Chatroom>()
        );


        Chatroom chatroom = new Chatroom(
                resultSetRoom.getLong("id"),
                resultSetRoom.getString("chat_name"),
                user,
                new ArrayList<Message>()
        );

        Optional<Message> message = Optional.of(
                new Message(
                        resultSetMessage.getLong("id"),
                        user,
                        chatroom,
                        resultSetMessage.getString("message") == null ? "" :
                                resultSetMessage.getString("message"),
                        resultSetMessage.getTimestamp("time") == null ? LocalDateTime.now() :
                                resultSetMessage.getTimestamp("time").toLocalDateTime()
                ));
        connection.close();
        return message;
    }
}