package school21.spring.service.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import school21.spring.service.models.User;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new User(resultSet.getLong("id"), resultSet.getString("email"));
    };

    public UsersRepositoryJdbcTemplateImpl(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    @Override
    public User findById(Long id) {
        User user = null;
        try {
            user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?;", ROW_MAPPER, id);
        } catch (DataAccessException dataAccessException) {
            System.out.println("Couldn't find User with id " + id);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users;", ROW_MAPPER);
    }

    @Override
    public void save(User entity) {
        String query = String.format("INSERT into users (email) VALUES ('%s');",
                entity.getEmail());
        jdbcTemplate.update(query);
    }

    @Override
    public void update(User entity) {
        String query = String.format("UPDATE users SET email = '%s' WHERE id = %d;",
                entity.getEmail(), entity.getId());
        jdbcTemplate.update(query);
    }

    @Override
    public void delete(Long id) {
        String query = String.format("DELETE FROM users WHERE id = %d;", id);
        jdbcTemplate.update(query);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = null;
        try {
            user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE email = ?;", ROW_MAPPER, email);
        } catch (DataAccessException dataAccessException) {
            System.out.println("Couldn't find User with email " + email);
        }
        return Optional.ofNullable(user);
    }
}