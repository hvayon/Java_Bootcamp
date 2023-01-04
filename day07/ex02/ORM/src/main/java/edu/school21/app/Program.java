package edu.school21.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.classes.OrmManager;
import edu.school21.classes.User;

import javax.sql.DataSource;
import java.sql.SQLException;

public class Program {

    public static DataSource makeHikariDataSource() {

        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        hikariDataSource.setUsername("postgres");
        hikariDataSource.setPassword("");
        return hikariDataSource;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        OrmManager ormManager = new OrmManager(makeHikariDataSource());
        ormManager.init();
        User user = new User(1L, "Tom", "Djery", 2);
        ormManager.save(user);
        user.setFirstName("Jack");
        ormManager.update(user);
        User user2;
        user2 = ormManager.findById(user.getId(), User.class);
        System.out.println(user2);
    }
}
