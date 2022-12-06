package com.decagon.fashionblog.services;

import com.decagon.fashionblog.entities.Users;
import com.decagon.fashionblog.pojos.UserDto;

public interface UserService {
    void createAdmin();
    Users registerUser(UserDto userDto);
    Users login(UserDto userDto);
    void changePassword(Long userId, UserDto userDto);
}
