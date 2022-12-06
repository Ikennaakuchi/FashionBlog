package com.decagon.fashionblog.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String postBody;

    @Column(name = "time_created")
    private LocalDateTime createdAt;

    @Column(name = "time_updated")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "usersId",
                foreignKey = @ForeignKey(name = "user_posts_id_fk"))
    private Users users;
    @OneToMany(mappedBy = "post",
                orphanRemoval = true,
                cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "post",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Likes> likesList = new ArrayList<>();

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", postBody='" + postBody + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", users=" + users +
                ", commentList=" + commentList +
                ", likesList=" + likesList +
                '}';
    }
}

