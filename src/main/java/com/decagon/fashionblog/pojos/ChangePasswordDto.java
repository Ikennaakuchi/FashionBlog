package com.decagon.fashionblog.pojos;

import lombok.Data;

@Data
public class ChangePasswordDto {
    private String username;
    private String password;
    private String password2;
}
