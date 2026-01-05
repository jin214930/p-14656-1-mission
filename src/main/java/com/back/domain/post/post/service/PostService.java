package com.back.domain.post.post.service;

import com.back.domain.post.post.document.Post;
import com.back.domain.post.post.repository.PostRepository;
import com.back.global.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public long count() {
        return postRepository.count();
    }

    public Post create(String title, String content, String author) {
        Post post = new Post(title, content, author);
        return postRepository.save(post);
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post getPost(String id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new DomainException(HttpStatus.NOT_FOUND.value(), "%s번 게시글을 찾을 수 없습니다.".formatted(id)));
    }

    public Post updatePost(String id, String title, String content) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new DomainException(HttpStatus.NOT_FOUND.value(), "%s번 게시글을 찾을 수 없습니다.".formatted(id)));

        if (title != null) {
            post.setTitle(title);
        }
        if (content != null) {
            post.setContent(content);
        }

        post.setModifiedDate(OffsetDateTime.now());

        return postRepository.save(post);
    }
}
