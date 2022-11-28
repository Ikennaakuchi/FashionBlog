package com.decagon.fashionblog.repositories;

import com.decagon.fashionblog.entities.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {
}
