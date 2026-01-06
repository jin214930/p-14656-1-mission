package com.back.domain.post.comment.repository;

import com.back.domain.post.comment.document.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CommentRepository extends ElasticsearchRepository<Comment, String> {
    List<Comment> findAll();

    List<Comment> findByPostId(String id);

    Page<Comment> findByPostId(String id, Pageable pageable);

    Page<Comment> findByPostIdAndContentContaining(String postId, String content, Pageable pageable);

    Page<Comment> findByPostIdAndAuthor(String postId, String author, Pageable pageable);

    Page<Comment> findByPostIdAndContentContainingOrPostIdAndAuthor(String postId1, String content, String postId2, String author, Pageable pageable);
}
