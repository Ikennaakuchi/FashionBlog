package com.decagon.fashionblog.serviceImpl;

import com.decagon.fashionblog.entities.Users;
import com.decagon.fashionblog.pojos.UserDto;
import com.decagon.fashionblog.repositories.UserRepository;
import com.decagon.fashionblog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void createAdmin() {
        Users users = new Users();
        users.setUserId(1L);
        users.setUsername("ADMIN");
        users.setPassword("password");
        userRepository.save(users);
    }

    @Override
    public void changePassword(Long userId, UserDto userDto){
        Users users = userRepository.findById(userId).get();
        users.setUserId(userId);
        users.setUsername(userDto.getUsername());
        users.setPassword(userDto.getPassword());
        userRepository.save(users);
    }

    @Override
    public Users registerUser(UserDto userDto) {
        Users users = new Users();
        users.setUsername(userDto.getUsername());
        users.setPassword(userDto.getPassword());
        return userRepository.save(users);
    }

    @Override
    public Users login(UserDto userDto) {
        return userRepository.findUserByUsernameAndPassword(userDto.getUsername(), userDto.getPassword())
                .orElse(null);
    }

}
