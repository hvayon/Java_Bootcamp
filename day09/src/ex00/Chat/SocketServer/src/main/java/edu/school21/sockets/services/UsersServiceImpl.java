package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("usersServiceImpl")
public class UsersServiceImpl implements UsersService {

    private UsersRepository repository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(@Qualifier("usersRepositoryImpl") UsersRepository repository,
                            @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String signUp(String username, String password) {

        String encodedPassword = passwordEncoder.encode(password);
        repository.save(new User(null, username, encodedPassword));
        return encodedPassword;

    }
}