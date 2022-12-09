package com.decagon.fashionblog.serviceImpl;

import com.decagon.fashionblog.entities.Comment;
import com.decagon.fashionblog.entities.Post;
import com.decagon.fashionblog.entities.Users;
import com.decagon.fashionblog.pojos.CommentDto;
import com.decagon.fashionblog.repositories.CommentRepository;
import com.decagon.fashionblog.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CommentServiceImplTest {

    @Mock
    CommentRepository commentRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    CommentServiceImpl commentService;

    Users user;
    Comment comment;
    CommentDto commentDto;


    @BeforeEach
    void init() {
    }

    @Test
    void createComment() {
        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(user));
        when(commentRepository.save(any(Comment.class)))
                .thenReturn(comment);
        Comment createdComment = commentService.createComment(commentDto, 1L, 2L);
        assertNotNull(createdComment);
        assertEquals("TESTING", createdComment.getCommentBody());
    }

    @Test
    void getAllPostComments() {
    }

    @Test
    void editComment() {
    }

    @Test
    void likeComment() {
    }

    @Test
    void deleteComment() {
    }
}