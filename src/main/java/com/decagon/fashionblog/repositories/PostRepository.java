package com.decagon.fashionblog.repositories;

import com.decagon.fashionblog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
//    @Query(nativeQuery = true, value = "SELECT * from post WHERE title LIKE %:postTitle%")
    List<Post> searchAllByTitleContainsIgnoreCase(String postTitle);
}
