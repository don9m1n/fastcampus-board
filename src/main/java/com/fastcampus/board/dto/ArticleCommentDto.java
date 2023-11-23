package com.fastcampus.board.dto;

import com.fastcampus.board.domain.ArticleComment;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
public record ArticleCommentDto(
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static ArticleCommentDto of(String content, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return ArticleCommentDto.builder()
                .content(content)
                .createdAt(createdAt)
                .createdBy(createdBy)
                .modifiedAt(modifiedAt)
                .modifiedBy(modifiedBy)
                .build();
    }
}