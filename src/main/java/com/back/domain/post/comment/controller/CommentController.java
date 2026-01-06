package com.back.domain.post.comment.controller;

import com.back.domain.post.comment.document.Comment;
import com.back.domain.post.comment.service.CommentService;
import com.back.domain.post.post.service.PostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;

    public record CreateCommentRequest(
            @NotBlank(message = "Content must not be blank")
            @Size(max = 500, min = 1)
            String content,
            @NotBlank(message = "Author must not be blank")
            @Size(max = 50, min = 1)
            String author
    ) {
    }

    @PostMapping
    public ResponseEntity<Comment> create(
            @PathVariable String postId,
            @RequestBody @Valid CreateCommentRequest request
    ) {
        Comment comment = commentService.create(
                postService.getPost(postId),
                request.content,
                request.author
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getComments(@PathVariable String postId) {
        postService.getPost(postId);
        List<Comment> comments = commentService.getCommentsByPost(postId);

        return ResponseEntity.ok(comments);
    }
}
