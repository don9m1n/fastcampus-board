package com.fastcampus.board.dto.response;

import com.fastcampus.board.dto.ArticleWithCommentsDto;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record ArticleWithCommentResponse(
        Long id,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String email,
        String nickname,
        Set<ArticleCommentResponse> articleCommentResponses
) implements Serializable {

    public static ArticleWithCommentResponse of(Long id, String title, String content, String hashtag, LocalDateTime createdAt, String email, String nickname, Set<ArticleCommentResponse> articleCommentResponses) {
        return ArticleWithCommentResponse.builder()
                .id(id)
                .title(title)
                .content(content)
                .hashtag(hashtag)
                .createdAt(createdAt)
                .email(email)
                .nickname(nickname)
                .articleCommentResponses(articleCommentResponses)
                .build();
    }

    public static ArticleWithCommentResponse from(ArticleWithCommentsDto dto) {
        String nickname = dto.userAccountDto().nickname();
        if (nickname == null || nickname.isBlank()) {
            nickname = dto.userAccountDto().userId();
        }

        return ArticleWithCommentResponse.builder()
                .id(dto.id())
                .title(dto.title())
                .content(dto.content())
                .hashtag(dto.hashtag())
                .createdAt(dto.createdAt())
                .email(dto.userAccountDto().email())
                .nickname(nickname)
                .articleCommentResponses(dto.articleCommentDtos().stream()
                        .map(ArticleCommentResponse::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)))
                .build();
    }

}