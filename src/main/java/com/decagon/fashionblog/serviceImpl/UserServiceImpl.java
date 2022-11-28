package com.decagon.fashionblog.serviceImpl;

import com.decagon.fashionblog.entities.User;
import com.decagon.fashionblog.exceptions.NotFoundException;
import com.decagon.fashionblog.pojos.LoginDto;
import com.decagon.fashionblog.pojos.SignUpDto;
import com.decagon.fashionblog.repositories.UserRepository;
import com.decagon.fashionblog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User signUp(SignUpDto signUpDto) {
        User user = new User();
        BeanUtils.copyProperties(signUpDto, user);

        return userRepository.save(user);
    }

    @Override
    public User login(LoginDto loginDto) {
        User user = userRepository.findUserByUsernameAndPassword(loginDto.getUserName(), loginDto.getPassword())
                .orElse(null);
        return user;
    }
}
