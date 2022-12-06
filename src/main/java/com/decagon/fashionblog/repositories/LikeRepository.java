package com.decagon.fashionblog.repositories;

import com.decagon.fashionblog.entities.Comment;
import com.decagon.fashionblog.entities.Likes;
import com.decagon.fashionblog.entities.Post;
import com.decagon.fashionblog.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {
Optional<Likes> findLikeByCommentAndUsers(Comment comment, Users users);
Optional<Likes> findLikeByPostAndUsers(Post post, Users users);
}

