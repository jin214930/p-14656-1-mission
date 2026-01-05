package com.back.global.document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.OffsetDateTime;

@Getter
@ToString
@EqualsAndHashCode
public class BaseDocument<T> implements Persistable<T> {
    @Id
    private T id;

    @Field(type = FieldType.Date, format = DateFormat.date_time)
    @CreatedDate
    private OffsetDateTime createdDate;

    @Field(type = FieldType.Date, format = DateFormat.date_time)
    @LastModifiedDate
    private OffsetDateTime modifiedDate;

    @Override
    public boolean isNew() {
        return id == null || (createdDate == null && modifiedDate == null);
    }
}
