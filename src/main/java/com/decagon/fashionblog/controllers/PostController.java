package com.decagon.fashionblog.controllers;

import com.decagon.fashionblog.entities.Post;
import com.decagon.fashionblog.pojos.PostDto;
import com.decagon.fashionblog.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create-post/{adminId}")
    public ResponseEntity<PostDto> createPost(@PathVariable Long adminId, @RequestBody PostDto postDto){
    postService.createPost(postDto, adminId);
    return new ResponseEntity<>(postDto, HttpStatus.OK);
}
    @GetMapping("/all-posts")
    public ResponseEntity<List<PostDto>> getAllPosts(){
        List<PostDto> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @GetMapping("/get-post/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long postId){
        PostDto postDto = postService.getPost(postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @PostMapping("/update-post/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable Long postId, @RequestBody PostDto postDto){
        postService.updatePost(postId, postDto);
        return new ResponseEntity<>("Post updated successfully", HttpStatus.OK);
    }

    @PutMapping("/{postId}/{userId}")
    public ResponseEntity<Void> likePost(@PathVariable Long postId, @PathVariable Long userId){
        postService.likePost(postId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return new ResponseEntity<>("Post deleted Successfully", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostDto>> searchPost(@RequestParam String q){
        List<PostDto> postDtoList = postService.searchPost(q);
        return new ResponseEntity<>(postDtoList, HttpStatus.FOUND);
    }
}
