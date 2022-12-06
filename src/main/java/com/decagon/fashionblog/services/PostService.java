package com.decagon.fashionblog.services;

import com.decagon.fashionblog.entities.Post;
import com.decagon.fashionblog.pojos.PostDto;

import java.util.List;

public interface PostService {
    Post createPost(PostDto postDto, Long adminId);
    List<PostDto> getAllPosts();
    PostDto getPost(Long postId);
    List<PostDto> searchPost(String title);
    Post updatePost(Long postId, PostDto postDto);
    void likePost(Long postId, Long userId);
    void deletePost(Long postId);
}
