package com.decagon.fashionblog.serviceImpl;

import com.decagon.fashionblog.entities.Post;
import com.decagon.fashionblog.entities.User;
import com.decagon.fashionblog.pojos.PostDto;
import com.decagon.fashionblog.repositories.PostRepository;
import com.decagon.fashionblog.repositories.UserRepository;
import com.decagon.fashionblog.services.PostService;
import com.tasks.week9task.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public Post createPost(PostDto postDto, String role) {
        User user = userRepository.findUserByRole(Roles.ADMIN.name()).orElse(null);
        Post post = new Post();
        post.setBody(postDto.getPostBody());
        post.setCreatedAt(LocalDateTime.now());
        post.setUser(user);
        return postRepository.save(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDto> postDto = new ArrayList<>();
        BeanUtils.copyProperties(posts, postDto);
        return postDto;
    }

    @Override
    public Post getPost() {
        return null;
    }

    @Override
    public Post updatePost() {
        return null;
    }

    @Override
    public void deletePost() {

    }
}
