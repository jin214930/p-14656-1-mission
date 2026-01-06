package com.back.domain.post.comment.document;

import com.back.global.document.BaseDocument;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "comments")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseDocument<String> {
    @Field(type = FieldType.Keyword)
    private String postId;

    @Field(type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Keyword)
    private String author;

    public Comment(String postId, String content, String author) {
        this.postId = postId;
        this.content = content;
        this.author = author;
    }
}
