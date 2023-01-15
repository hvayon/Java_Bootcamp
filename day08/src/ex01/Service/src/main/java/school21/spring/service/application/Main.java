package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        UsersRepository repositoryJdbc = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        UsersRepository repositoryJdbcTempl = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);

        StringBuilder builder = new StringBuilder();
        builder.append("drop table if exists users;\n ")
                .append("create table if not exists users (\n")
                .append("id     serial primary key ,\n")
                .append("email  text not null\n")
                .append(");")
                .append("insert into users (email) VALUES ('michaelschumacher@gmail.com');\n")
                .append("insert into users (email) VALUES ('landonorris@gmail.com');\n")
                .append("insert into users (email) VALUES ('georgerussell@gmail.com');\n");

        DataSource ds = context.getBean("hikariDataSource", com.zaxxer.hikari.HikariDataSource.class);
        try (Connection connection = ds.getConnection();
            Statement statement = connection.createStatement())   {
            statement.execute(builder.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Find all users in the table: ");
        System.out.println(repositoryJdbc.findAll());
        System.out.println(repositoryJdbcTempl.findAll());

        System.out.println("Finding users by id: ");
        System.out.println(repositoryJdbc.findById(2L));
        System.out.println(repositoryJdbcTempl.findById(2L));

        System.out.println("Create user hvayon... ");
        repositoryJdbc.update(new User(1L, "hvayon@student.21-school.ru"));
        repositoryJdbcTempl.update(new User(1L, "hvayon@student.21-school.ru"));
        System.out.println("Find user hvayon by id: ");
        System.out.println(repositoryJdbc.findById(1L));
        System.out.println(repositoryJdbcTempl.findById(1L));

        System.out.println("Save new users: ");
        String newEmail1 = "new@user1.ru";
        repositoryJdbc.save(new User(null, newEmail1));
        System.out.println(repositoryJdbc.findByEmail(newEmail1));

        String newEmail2 = "new@user2.ru";
        repositoryJdbcTempl.save(new User(null, newEmail2));
        System.out.println(repositoryJdbcTempl.findByEmail(newEmail2));

        System.out.println("Delete users number 2: ");
        repositoryJdbc.delete(2L);
        repositoryJdbcTempl.delete(2L);
        System.out.println(repositoryJdbc.findAll());
        System.out.println(repositoryJdbcTempl.findAll());
    }
}
