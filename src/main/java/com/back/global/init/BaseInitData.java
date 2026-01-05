package com.back.global.init;

import com.back.domain.post.comment.document.Comment;
import com.back.domain.post.comment.service.CommentService;
import com.back.domain.post.post.document.Post;
import com.back.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class BaseInitData {
    private final PostService postService;
    private final CommentService commentService;

    @Bean
    public ApplicationRunner baseInitDataRunner() {
        return args -> {
            work1();
            work2();
            work3();
            work4();
            work5();
            work6();
            work7();
        };
    }

    private void work1() {
        if (postService.count() == 0) {
            for (int i = 1; i <= 10; i++) {
                String title = "Sample Post Title " + i;
                String content = "This is the content of sample post number " + i + ".";
                String author = "Author" + i;
                Post post = postService.create(title, content, author);
                log.debug("Created Post: {}", post);
            }
        }

        log.debug("Post entity 개수: {}", postService.count());
    }

    private void work2() {
        log.debug("전체 Post 조회");

        for (Post post : postService.getPosts()) {
            log.debug("Post: {}", post);
        }
    }

    private void work3() {
        log.debug("Post 단건 조회");

        for (Post post : postService.getPosts()) {
            Post fetchedPost = postService.getPost(post.getId());
            log.debug("조회된 Post: {}", fetchedPost);
        }
    }

    private void work4() {
        log.debug("Post 수정");

        for (Post post : postService.getPosts()) {
            String newTitle = post.getTitle() + " [Updated]";
            String newContent = post.getContent() + " This content has been updated.";
            Post updatedPost = postService.updatePost(post.getId(), newTitle, newContent);
            log.debug("Updated Post: {}", updatedPost);
        }
    }

    private void work5() {
        log.debug("Post 삭제");
        for (Post post : postService.getPosts()) {
            postService.delete(post.getId());
            log.debug("Deleted Post: {}", post.getId());
        }

        log.debug("삭제 후 Post 개수: {}", postService.count());
    }

    private void work6() {
        if (commentService.count() == 0) {
            log.debug("샘플 Comment 데이터 생성");
            for (int i = 1; i <= 5; i++) {
                Post post = postService.create("Post for Comment " + i, "Content for post " + i, "Author" + i);
                String content = "This is a comment number " + i + " for post " + post.getId();
                String author = "Commenter" + i;
                Comment comment = commentService.create(post, content, author);
                log.debug("Created Comment: {}", comment);
            }
        }

        log.debug("Comment 개수: {}", commentService.count());
    }

    private void work7() {
        log.debug("Comment 전체 조회");

        for (Comment comment : commentService.getComments()) {
            log.debug("Existing Comment: {}", comment);
        }
    }
}
