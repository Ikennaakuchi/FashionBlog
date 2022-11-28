package com.decagon.fashionblog.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "likes")
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",
                referencedColumnName = "id",
                nullable = false,
                foreignKey = @ForeignKey(name = "user_like_id_fk"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id",
                referencedColumnName = "id",
                nullable = false,
                foreignKey = @ForeignKey(name = "post_like_id_fk"))
    private Post post;

    @ManyToOne
    @JoinColumn(name = "comment_id",
                referencedColumnName = "id",
                nullable = false,
                foreignKey = @ForeignKey(name = "comment_like_id_fk"))
    private Comment comment;

    @Override
    public String toString() {
        return "Likes{" +
                "id=" + id +
                ", user=" + user +
                ", post=" + post +
                ", comment=" + comment +
                '}';
    }
}
