package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private final DataSource ds;

    public UsersRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public User findById(Long id) {
        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement()) {

            String query = String.format("SELECT * FROM users WHERE id = %d;", id);
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();

            return new User(
                    resultSet.getLong(1),
                    resultSet.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM users;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getLong(1),
                        resultSet.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void save(User entity) {
        String query = String.format("INSERT into users (email) VALUES ('%s');",
                entity.getEmail());

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        String query = String.format("UPDATE users SET email = '%s' WHERE id = %d;",
                entity.getEmail(), entity.getId());

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String query = String.format("DELETE FROM users WHERE id = %d;", id);

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> optionalUser;

        try (Connection connection = ds.getConnection();
             Statement statement = connection.createStatement()) {

            String query = String.format("SELECT * FROM users WHERE email = '%s';", email);
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();

            optionalUser = Optional.of(new User(
                    resultSet.getLong(1),
                    resultSet.getString(2)));
            return optionalUser;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
