package com.decagon.fashionblog.services;

import com.decagon.fashionblog.entities.Likes;

public interface LikeService {
    void addLike(Likes likes);
    void removeLike(Long likeId);
}
