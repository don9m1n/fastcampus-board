package com.fastcampus.board.dto;

import com.fastcampus.board.domain.Article;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record ArticleWithCommentsDto(
        Long id,
        UserAccountDto userAccountDto,
        Set<ArticleCommentDto> articleCommentDtos,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static ArticleWithCommentsDto of(Long id, UserAccountDto userAccountDto, Set<ArticleCommentDto> articleCommentDtos, String title, String content, String hashtag, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return ArticleWithCommentsDto.builder()
                .id(id)
                .userAccountDto(userAccountDto)
                .articleCommentDtos(articleCommentDtos)
                .title(title)
                .content(content)
                .hashtag(hashtag)
                .createdAt(createdAt)
                .createdBy(createdBy)
                .modifiedAt(modifiedAt)
                .modifiedBy(modifiedBy)
                .build();
    }

    public static ArticleWithCommentsDto from(Article entity) {
        return ArticleWithCommentsDto.builder()
                .id(entity.getId())
                .userAccountDto(UserAccountDto.from(entity.getUserAccount()))
                .articleCommentDtos(entity.getArticleComments().stream()
                        .map(ArticleCommentDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)))
                .title(entity.getTitle())
                .content(entity.getContent())
                .hashtag(entity.getHashtag())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .modifiedAt(entity.getModifiedAt())
                .modifiedBy(entity.getModifiedBy())
                .build();
    }
}
