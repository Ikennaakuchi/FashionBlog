package com.decagon.fashionblog.controllers;

import com.decagon.fashionblog.entities.Comment;
import com.decagon.fashionblog.pojos.CommentDto;
import com.decagon.fashionblog.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/create-comment/{userId}/{postId}")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long userId, @PathVariable Long postId, @RequestBody CommentDto commentDto){
        commentService.createComment(commentDto, userId, postId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @GetMapping("/post-comments/{postId}")
    public ResponseEntity<List<CommentDto>> getAllPostComments(@PathVariable Long postId){
        List<CommentDto> comments = commentService.getAllPostComments(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PatchMapping("/edit/{commentId}")
    public ResponseEntity<CommentDto> editComment(@PathVariable Long commentId, @RequestBody CommentDto commentDto){
        commentService.editComment(commentId, commentDto);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @PutMapping("/like/{commentId}/{userId}")
    public ResponseEntity<Void> likeComment(@PathVariable Long commentId, @PathVariable Long userId){
        commentService.likeComment(commentId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
