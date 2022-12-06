package com.decagon.fashionblog.serviceImpl;

import com.decagon.fashionblog.entities.Comment;
import com.decagon.fashionblog.entities.Likes;
import com.decagon.fashionblog.entities.Post;
import com.decagon.fashionblog.entities.Users;
import com.decagon.fashionblog.exceptions.NotFoundException;
import com.decagon.fashionblog.exceptions.Notification;
import com.decagon.fashionblog.pojos.CommentDto;
import com.decagon.fashionblog.repositories.CommentRepository;
import com.decagon.fashionblog.repositories.LikeRepository;
import com.decagon.fashionblog.repositories.PostRepository;
import com.decagon.fashionblog.repositories.UserRepository;
import com.decagon.fashionblog.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    @Override
    public Comment createComment(CommentDto commentDto, Long userId, Long postId) {
        Post post = postRepository.findById(postId).get();
        Users users = userRepository.findById(userId)
                .orElseThrow(()-> new NotFoundException("Users does not exist"));
            Comment comment = new Comment();
            comment.setPost(post);
            comment.setCommentOwner(users.getUsername());
            comment.setUsers(users);
            comment.setCommentBody(commentDto.getCommentBody());
            comment.setCreatedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> getAllPostComments(Long postId) {
        List<Comment> comments = commentRepository.findCommentByPostPostId(postId);
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : comments){
            CommentDto commentDto = new CommentDto();
            BeanUtils.copyProperties(comment, commentDto);
            commentDtos.add(commentDto);
        }
        return commentDtos;
    }

    @Override
    public Comment editComment(Long commentId, CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new NotFoundException("Comment Not Found"));
        comment.setCommentBody(commentDto.getCommentBody());
        comment.setUpdatedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @Override
    public void likeComment(Long commentId, Long userId) {
        Comment likedComment = commentRepository.findById(commentId).get();
        Users currentUsers = userRepository.findById(userId)
                .orElseThrow(()-> new Notification("Please enter your username"));

        Optional<Likes> liked = likeRepository.findLikeByCommentAndUsers(likedComment, currentUsers);

        if(liked.isPresent()){
            likeRepository.deleteById(liked.get().getLikeId());
        } else {
            Likes newLikes = new Likes();
            newLikes.setComment(likedComment);
            newLikes.setUsers(currentUsers);
            likeRepository.save(newLikes);
        }
    }
    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
