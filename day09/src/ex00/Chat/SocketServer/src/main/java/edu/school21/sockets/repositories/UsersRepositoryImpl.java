package edu.school21.sockets.repositories;
import edu.school21.sockets.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component("usersRepositoryImpl")
public class UsersRepositoryImpl implements UsersRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryImpl(@Qualifier("hikariDataSource") DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM repo.user WHERE id=?", new Object[]{id},
                (resSet, rowNumber) -> new User(resSet.getLong("id"), resSet.getString("username"),
                        resSet.getString("password"))).stream().findAny();
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update("INSERT INTO repo.user (username, password) VALUES (?, ?)", entity.getUsername(), entity.getPassword());
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM repo.user", (resSet, rowNumber) -> new User(resSet.getLong("id"),
                resSet.getString("username"), resSet.getString("password")));
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("UPDATE repo.user SET username=?, password=? WHERE id=?",
                entity.getUsername(), entity.getPassword(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM repo.user WHERE id=?", id);
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return jdbcTemplate.query("SELECT * FROM repo.user WHERE username=?", new Object[]{username},
                (resSet, rowNumber) -> new User(resSet.getLong("id"),
                        resSet.getString("username"), resSet.getString("password"))).stream().findAny();
    }
}