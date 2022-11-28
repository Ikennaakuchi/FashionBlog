package com.decagon.fashionblog.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment_body")
    private String commentBody;

    @ManyToOne
    @JoinColumn(name = "post_id",
                referencedColumnName = "id",
                nullable = false,
                foreignKey = @ForeignKey(name = "post_comment_id_fk"))
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id",
                referencedColumnName = "id",
                nullable = false,
                foreignKey = @ForeignKey(name = "user_comment_id_fk"))
    private User user;

    @OneToMany(mappedBy = "comment",
                orphanRemoval = true,
                cascade = CascadeType.ALL)
    private List<Likes> likes = new ArrayList<>();

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", commentBody='" + commentBody + '\'' +
                ", posts=" + post +
                ", user=" + user +
                '}';
    }
}
