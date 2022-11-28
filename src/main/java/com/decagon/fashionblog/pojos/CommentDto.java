package com.decagon.fashionblog.pojos;

import com.decagon.fashionblog.entities.User;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class CommentDto {
    private User commentOwner;
    private String commentBody;
    private LocalDateTime createdAt;
}
