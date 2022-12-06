package com.decagon.fashionblog.repositories;

import com.decagon.fashionblog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentByPostPostId(Long postId);

}
