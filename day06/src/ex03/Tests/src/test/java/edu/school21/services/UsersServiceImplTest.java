package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import exceptions.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UsersServiceImplTest {

    UsersRepository usersRepository = Mockito.mock(UsersRepository.class);
    UsersServiceImpl usersService = new UsersServiceImpl(usersRepository);
    User user;

    @BeforeEach
    void initMockito () {
        user = new User(1L, "correctLogin", "correctPassword", false);
        when(usersRepository.findByLogin("correctLogin")).thenReturn(user);
        when(usersRepository.findByLogin("incorrectLogin")).thenThrow(new EntityNotFoundException("Incorrect login or password"));
    }

    @Test
    void correctAuthenticateTest() {
        assertTrue(usersService.authenticate("correctLogin", "correctPassword"));
        Mockito.verify(usersRepository).update(user);
    }

    @Test
    void incorrectLoginTest() {
        assertThrows(EntityNotFoundException.class, () -> {
            usersService.authenticate("incorrectLogin", "correctPassword");
        });
    }

    @Test
    void incorrectPasswordTest() {
        assertFalse(usersService.authenticate("correctLogin", "incorrectPassword"));
    }

    @Test
    void authentificateTestTrue() {
        user.setAuthentication(true);
        assertThrows(AlreadyAuthenticatedException.class, () -> {
            usersService.authenticate("correctLogin", "correctPassword");
        });
    }
}

