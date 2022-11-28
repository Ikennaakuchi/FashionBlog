package com.decagon.fashionblog.pojos;

import lombok.Data;

@Data
public class SignUpDto {
    private String firstName;
    private String lastName;
    private String role;
    private String userName;
    private String email;
    private String password;
}
