package com.decagon.fashionblog.serviceImpl;

import com.decagon.fashionblog.entities.*;
import com.decagon.fashionblog.exceptions.NotFoundException;
import com.decagon.fashionblog.exceptions.Notification;
import com.decagon.fashionblog.pojos.PostDto;
import com.decagon.fashionblog.repositories.LikeRepository;
import com.decagon.fashionblog.repositories.PostRepository;
import com.decagon.fashionblog.repositories.UserRepository;
import com.decagon.fashionblog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    @Override
    public Post createPost(PostDto postDto, Long adminId) {
        if (!(adminId == 1L)){
            throw new Notification("Not Authorized");
        }
        Users users = userRepository.findById(adminId)
                .orElseThrow(()-> new NotFoundException("Not found"));
        Post post = new Post();
        post.setTitle(postDto.getPostTitle());
        post.setPostBody(postDto.getPostBody());
        post.setUsers(users);
        post.setCreatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDto> postDtoList = new ArrayList<>();
        for (Post post: posts){
            PostDto postDto = new PostDto();
            postDto.setPostTitle(post.getTitle());
            postDto.setPostBody(post.getPostBody());
            postDtoList.add(postDto);
        }

        return postDtoList;
    }

    @Override
    public PostDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new NotFoundException("Post not found"));
        PostDto postDto = new PostDto();
        BeanUtils.copyProperties(post, postDto);
        return postDto;
    }

    @Override
    public List<PostDto> searchPost(String q) {
        List<Post> posts = postRepository.searchAllByTitleContainsIgnoreCase(q);
        List<PostDto> postDtoList = new ArrayList<>();
        for(Post post: posts){
            PostDto postDto = new PostDto();
            postDto.setPostTitle(post.getTitle());
            postDto.setPostBody(post.getPostBody());
            postDtoList.add(postDto);
        }
        return postDtoList;
    }

    @Override
    public Post updatePost(Long postId, PostDto postDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new NotFoundException("Post Not Found"));
        BeanUtils.copyProperties(postDto, post);
        post.setUpdatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Override
    public void likePost(Long postId, Long userId) {
        Post likedPost = postRepository.findById(postId).get();
        Users currentUsers = userRepository.findById(userId)
                .orElseThrow(()-> new Notification("Please enter your username"));

        Optional<Likes> liked = likeRepository.findLikeByPostAndUsers(likedPost, currentUsers);

        if(liked.isPresent()){
            likeRepository.deleteById(liked.get().getLikeId());
        } else {
            Likes newLikes = new Likes();
            newLikes.setPost(likedPost);
            newLikes.setUsers(currentUsers);
            likeRepository.save(newLikes);
        }
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
