package com.decagon.fashionblog.services;

import com.decagon.fashionblog.entities.Post;
import com.decagon.fashionblog.pojos.PostDto;

import java.util.List;

public interface PostService {
    Post createPost(PostDto postDto, String role);
    List<PostDto> getAllPosts();
    Post getPost();
    Post updatePost();
    void deletePost();
}
