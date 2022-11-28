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
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "body")
    private String body;

    @Column(name = "time_created")
    private LocalDateTime createdAt;

    @Column(name = "time_updated")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id",
                referencedColumnName = "id",
                nullable = false,
                foreignKey = @ForeignKey(name = "post_user_id_fk"))
    private User user;

    @OneToMany(mappedBy = "post",
                orphanRemoval = true,
                cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Likes> likes = new ArrayList<>();

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", user=" + user +
                ", comments=" + comments +
                ", likes=" + likes +
                '}';
    }
}
