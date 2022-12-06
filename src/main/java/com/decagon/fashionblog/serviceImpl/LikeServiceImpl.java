package com.decagon.fashionblog.serviceImpl;

import com.decagon.fashionblog.entities.Likes;
import com.decagon.fashionblog.repositories.LikeRepository;
import com.decagon.fashionblog.services.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    @Override
    public void addLike(Likes likes) {
        likeRepository.save(likes);
    }

    @Override
    public void removeLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
