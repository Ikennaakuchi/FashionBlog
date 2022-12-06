package com.decagon.fashionblog.services;

import com.decagon.fashionblog.entities.Comment;
import com.decagon.fashionblog.pojos.CommentDto;
import com.decagon.fashionblog.pojos.PostDto;

import java.util.List;

public interface CommentService {
    public Comment createComment(CommentDto commentDto, Long userId, Long postId);
    List<CommentDto> getAllPostComments(Long postId);
    Comment editComment(Long commentId, CommentDto commentDto);
    void deleteComment(Long commentId);
    void likeComment(Long commentId, Long userId);
}
