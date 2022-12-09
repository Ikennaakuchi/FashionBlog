package com.decagon.fashionblog.serviceImpl;

import com.decagon.fashionblog.entities.Likes;
import com.decagon.fashionblog.entities.Post;
import com.decagon.fashionblog.entities.Users;
import com.decagon.fashionblog.pojos.PostDto;
import com.decagon.fashionblog.repositories.LikeRepository;
import com.decagon.fashionblog.repositories.PostRepository;
import com.decagon.fashionblog.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class PostServiceImplTest {

    @Mock
    PostRepository postRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    LikeRepository likeRepository;

    @InjectMocks
    PostServiceImpl postService;

    Post post;
    Post post1;
    Post post2;
    PostDto postDto;
    Users user;
    List<Post> postList;

    Likes like;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        post = new Post();
        post.setTitle("TESTING");
        post.setPostBody("testing post service");

        post1 = new Post();
        post1.setTitle("TESTING1");
        post1.setPostBody("testing post service");

        post2 = new Post();
        post2.setTitle("TESTING2");
        post2.setPostBody("testing post service");

        postList = new ArrayList<>();
        postList.add(post);
        postList.add(post1);
        postList.add(post2);


        postDto = new PostDto();
        postDto.setPostTitle("TESTING");
        postDto.setPostBody("testing the post service");

        user = new Users();
        like = new Likes();
    }

    @Test
    void createPost() {
        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(user));
        when(postRepository.save(any(Post.class)))
                .thenReturn(post);
        Post createdPost = postService.createPost(postDto, 1L);
        assertNotNull(createdPost);
        assertEquals("TESTING", createdPost.getTitle());
    }

    @Test
    void getAllPosts() {
        when(postRepository.findAll())
                .thenReturn(postList);
        List<PostDto> allPosts = postService.getAllPosts();
        assertEquals(3, allPosts.size());
    }

    @Test
    void getPost() {
        when(postRepository.findById(anyLong()))
                .thenReturn(Optional.of(post));
        PostDto newPost = postService.getPost(1L);
        assertNotNull(newPost);

    }

    @Test
    void searchPost() {
        when(postRepository.searchAllByTitleContainsIgnoreCase(anyString()))
                .thenReturn(postList);
        List<PostDto> searchedPost = postService.searchPost("any String");
        assertNotNull(searchedPost);
        assertEquals(3, searchedPost.size());
    }

    @Test
    void updatePost() {
        when(postRepository.findById(anyLong()))
                .thenReturn(Optional.of(post));
        when(postRepository.save(any(Post.class)))
                .thenReturn(post);
        Post updatedPost = postService.updatePost(1L,postDto);
        assertNotNull(updatedPost);
        assertEquals("TESTING", updatedPost.getTitle());
    }

    @Test
    void likePost() {
        when(postRepository.findById(anyLong()))
                .thenReturn(Optional.of(post));
        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(user));
        when(likeRepository.findLikeByPostAndUsers(post, user))
                .thenReturn(Optional.of(like));
        when(likeRepository.save(any(Likes.class)))
                .thenReturn(like);
        postService.likePost(1L, 2L);
        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    void deletePost() {
        postService.deletePost(1L);
        verify(postRepository, times(1)).deleteById(anyLong());
    }
}