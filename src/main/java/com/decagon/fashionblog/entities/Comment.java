package com.decagon.fashionblog.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "comment_owner")
    private String commentOwner;
    @Column(name = "comment_body")
    private String commentBody;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "postId",
                nullable = false,
                foreignKey = @ForeignKey(name = "post_comment_id_fk"))
    private Post post;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId",
            nullable = false,
            foreignKey = @ForeignKey(name = "user_comment_id_fk"))
    private Users users;

    @OneToMany(mappedBy = "comment",
                orphanRemoval = true,
                cascade = CascadeType.ALL)
    private List<Likes> likesList = new ArrayList<>();

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + commentId +
                ", commentOwner='" + commentOwner + '\'' +
                ", commentBody='" + commentBody + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", post=" + post +
                ", users=" + users +
                ", likesList=" + likesList +
                '}';
    }
}

