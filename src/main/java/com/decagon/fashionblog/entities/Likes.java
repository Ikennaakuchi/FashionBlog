package com.decagon.fashionblog.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long likeId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userId",
                foreignKey = @ForeignKey(name = "user_likes_id_fk"))
    private Users users;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "postId",
                foreignKey = @ForeignKey(name = "post_likes_id_fk"))
    private Post post;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "commentId",
                foreignKey = @ForeignKey(name = "comment_like_id_fk"))
    private Comment comment;
    @Override
    public String toString() {
        return "Likes{" +
                "id=" + likeId +
                ", users=" + ((users.getUserId() != null) ? users.getUserId() : null) +
                ", post=" + ((post.getPostId() != null) ? post.getPostId() : null)  +
                ", comment=" + ((comment.getCommentId() != null) ? comment.getCommentId() : null) +
                '}';
    }
}
