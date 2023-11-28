package com.fastcampus.board.dto;

import com.fastcampus.board.domain.Article;
import com.fastcampus.board.domain.ArticleComment;
import com.fastcampus.board.domain.UserAccount;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ArticleCommentDto(
        Long id,
        Long articleId,
        UserAccountDto userAccountDto,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static ArticleCommentDto of(Long id, Long articleId, UserAccountDto userAccountDto, String content, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return ArticleCommentDto.builder()
                .id(id)
                .articleId(articleId)
                .userAccountDto(userAccountDto)
                .content(content)
                .createdAt(createdAt)
                .createdBy(createdBy)
                .modifiedAt(modifiedAt)
                .modifiedBy(modifiedBy)
                .build();
    }

    public static ArticleCommentDto from(ArticleComment entity) {
        return ArticleCommentDto.builder()
                .id(entity.getId())
                .articleId(entity.getArticle().getId())
                .userAccountDto(UserAccountDto.from(entity.getUserAccount()))
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .modifiedAt(entity.getModifiedAt())
                .modifiedBy(entity.getModifiedBy())
                .build();
    }

    public static ArticleCommentDto of(Long articleId, UserAccountDto userAccountDto, String content) {
        return ArticleCommentDto.builder()
                .articleId(articleId)
                .userAccountDto(userAccountDto)
                .content(content)
                .build();
    }

    public ArticleComment toEntity(Article entity, UserAccount userAccount) {
        return ArticleComment.of(
                entity,
                userAccount,
                content
        );
    }

}