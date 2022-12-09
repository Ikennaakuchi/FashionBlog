package com.decagon.fashionblog.serviceImpl;

import com.decagon.fashionblog.entities.Users;
import com.decagon.fashionblog.pojos.UserDto;
import com.decagon.fashionblog.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    Users user;
    UserDto userDto;


    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        user = new Users();
        user.setUserId(1L);
        user.setUsername("HOPE");
        user.setPassword("password");

        userDto = new UserDto();
        userDto.setUsername("HOPE");
        userDto.setPassword("password");
    }

    @Test
    void createAdmin() {
        userService.createAdmin();
        verify(userRepository, times(1)).save(any(Users.class));
    }

    @Test
    void changePassword() {
        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(user));

        userService.changePassword(1L,userDto);
        verify(userRepository, times(1)).save(any(Users.class));
    }

    @Test
    void registerUser() {
        when(userRepository.save(any(Users.class)))
                .thenReturn(user);
        Users registeredUser = userService.registerUser(userDto);
        assertEquals("HOPE", registeredUser.getUsername());
    }

    @Test
    void login() {
        when(userRepository.findUserByUsernameAndPassword(anyString(), anyString()))
                .thenReturn(Optional.of(user));

        Users loggedInUser = userService.login(userDto);
        assertNotNull(loggedInUser);
        assertEquals("HOPE", loggedInUser.getUsername());
    }
}