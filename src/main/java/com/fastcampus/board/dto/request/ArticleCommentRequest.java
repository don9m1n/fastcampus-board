package com.fastcampus.board.dto.request;

import com.fastcampus.board.domain.ArticleComment;
import com.fastcampus.board.dto.ArticleCommentDto;
import com.fastcampus.board.dto.UserAccountDto;
import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link com.fastcampus.board.domain.ArticleComment}
 */
@Builder
public record ArticleCommentRequest(Long articleId, String content) {

    public static ArticleCommentRequest of(Long articleId, String content) {
        return ArticleCommentRequest.builder()
                .articleId(articleId)
                .content(content)
                .build();
    }

    public ArticleCommentDto toDto(UserAccountDto userAccountDto) {
        return ArticleCommentDto.of(
                articleId,
                userAccountDto,
                content
        );
    }
}