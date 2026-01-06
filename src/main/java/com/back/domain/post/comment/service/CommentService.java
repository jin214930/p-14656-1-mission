package com.back.domain.post.comment.service;

import com.back.domain.post.comment.document.Comment;
import com.back.domain.post.comment.repository.CommentRepository;
import com.back.domain.post.post.document.Post;
import com.back.domain.post.post.repository.PostRepository;
import com.back.global.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public long count() {
        return commentRepository.count();
    }

    public Comment create(Post post, String content, String author) {
        Comment comment = new Comment(post.getId(), content, author);
        return commentRepository.save(comment);
    }

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public Comment getComment(String id) {
        return findById(id);
    }

    public List<Comment> getCommentsByPost(String id) {
        return commentRepository.findByPostId(id);
    }

    public Comment updateComment(String id, String content) {
        Comment comment = findById(id);

        if (content != null) {
            comment.setContent(content);
        }

        return commentRepository.save(comment);
    }

    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    private Comment findById(String id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new DomainException(HttpStatus.NOT_FOUND.value(), "%s번 댓글을 찾을 수 없습니다.".formatted(id)));
    }
}
