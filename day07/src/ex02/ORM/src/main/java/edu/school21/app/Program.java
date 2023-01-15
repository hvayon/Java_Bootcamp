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
        User user1 = new User(1L, "Natalia", "Hvayon", 18);
        User user2 = new User(2L, "Nick", "FreshBoy", 20);
        User user4 = new User(3L, "Prosto", "Chelick", 100500);
        ormManager.save(user1);
        ormManager.save(user2);
        ormManager.save(user4);
        user1.setFirstName("Lola");
        ormManager.update(user1);
        User user3;
        user3 = ormManager.findById(user1.getId(), User.class);
        System.out.println(user3);
    }
}
