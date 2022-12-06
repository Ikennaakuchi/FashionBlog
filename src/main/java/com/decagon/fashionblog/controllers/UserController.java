package com.decagon.fashionblog.controllers;

import com.decagon.fashionblog.entities.Users;
import com.decagon.fashionblog.pojos.UserDto;
import com.decagon.fashionblog.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto){
       userService.registerUser(userDto);
      return new ResponseEntity<>("Registration successful", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto){
        Users users = userService.login(userDto);
        if (users == null){
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Successful login", HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<String> createAdmin(){
        userService.createAdmin();
        return new ResponseEntity<>("Welcome, please log in", HttpStatus.CREATED);
    }

    @PostMapping("/change-password/{userId}")
    public ResponseEntity<String> changePassword(@PathVariable Long userId, @RequestBody UserDto userDto){
        userService.changePassword(userId, userDto);
        return new ResponseEntity<>("Password changed successfully", HttpStatus.CREATED);
    }
}
