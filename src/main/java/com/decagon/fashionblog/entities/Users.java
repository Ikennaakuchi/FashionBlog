package com.decagon.fashionblog.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "users",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Likes> likesList = new ArrayList<>();

    @OneToMany(mappedBy = "users",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    @Override
    public String toString() {
        return "Users{" +
                "id=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
