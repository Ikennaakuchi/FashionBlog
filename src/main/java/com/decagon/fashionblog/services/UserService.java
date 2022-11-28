package com.decagon.fashionblog.services;

import com.decagon.fashionblog.entities.User;
import com.decagon.fashionblog.pojos.LoginDto;
import com.decagon.fashionblog.pojos.SignUpDto;

public interface UserService {
    User signUp(SignUpDto signUpDto);
    User login(LoginDto loginDto);
}
