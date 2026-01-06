package com.back.domain.post.comment.controller;

import com.back.domain.post.comment.document.Comment;
import com.back.domain.post.comment.service.CommentService;
import com.back.domain.post.post.service.PostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<Comment>> getComments(
            @PathVariable String postId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        postService.getPost(postId);
        Page<Comment> comments = commentService.getCommentsByPost(postId, page, size);

        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable String postId, @PathVariable String id) {
        postService.getPost(postId);
        return ResponseEntity.ok(commentService.getComment(id));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Comment>> search(
            @PathVariable String postId,
            @RequestParam String keyword,
            @RequestParam(defaultValue = "contentAndAuthor") String searchType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        postService.getPost(postId);
        Page<Comment> comments = commentService.search(postId, keyword, searchType, page, size);
        return ResponseEntity.ok(comments);
    }

    public record UpdateCommentRequest(
            @NotBlank(message = "Content must not be blank")
            @Size(max = 500, min = 1)
            String content
    ) {
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(
            @PathVariable String postId,
            @PathVariable String id,
            @RequestBody @Valid UpdateCommentRequest request
    ) {
        postService.getPost(postId);
        Comment comment = commentService.updateComment(id, request.content);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable String postId, @PathVariable String id) {
        postService.getPost(postId);
        commentService.deleteComment(commentService.getComment(id));
        return ResponseEntity.noContent().build();
    }
}
