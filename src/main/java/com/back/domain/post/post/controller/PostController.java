package com.back.domain.post.post.controller;

import com.back.domain.post.post.document.Post;
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
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    record CreatePostRequest(
            @NotBlank(message = "Title must not be blank")
            @Size(max = 100, min = 1)
            String title,
            @NotBlank(message = "Content must not be blank")
            String content,
            @NotBlank(message = "Author must not be blank")
            String author
    ) {
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody @Valid CreatePostRequest request) {
        Post post = postService.create(request.title, request.content, request.author);

        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping
    public ResponseEntity<Page<Post>> getPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Post> posts = postService.getPosts(page, size);

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable String id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    record UpdatePostRequest(
            @NotBlank(message = "Title must not be blank")
            @Size(max = 100, min = 1)
            String title,
            @NotBlank(message = "Content must not be blank")
            String content
    ) {
    }

    @GetMapping("/search")
    public Page<Post> search(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "titleAndContent") String searchType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return postService.search(keyword, searchType, page, size);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> update(
            @PathVariable String id,
            @RequestBody @Valid UpdatePostRequest request
    ) {
        Post post = postService.updatePost(
                id,
                request.title,
                request.content
        );

        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        postService.deletePost(id);

        return ResponseEntity.noContent().build();
    }
}
