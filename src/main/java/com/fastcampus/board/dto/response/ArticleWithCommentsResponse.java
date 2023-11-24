package com.fastcampus.board.dto.response;

import com.fastcampus.board.dto.ArticleWithCommentsDto;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record ArticleWithCommentsResponse(
        Long id,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String email,
        String nickname,
        Set<ArticleCommentResponse> articleCommentsResponse
) implements Serializable {

    public static ArticleWithCommentsResponse of(Long id, String title, String content, String hashtag, LocalDateTime createdAt, String email, String nickname, Set<ArticleCommentResponse> articleCommentResponses) {
        return ArticleWithCommentsResponse.builder()
                .id(id)
                .title(title)
                .content(content)
                .hashtag(hashtag)
                .createdAt(createdAt)
                .email(email)
                .nickname(nickname)
                .articleCommentsResponse(articleCommentResponses)
                .build();
    }

    public static ArticleWithCommentsResponse from(ArticleWithCommentsDto dto) {
        String nickname = dto.userAccountDto().nickname();
        if (nickname == null || nickname.isBlank()) {
            nickname = dto.userAccountDto().userId();
        }

        return ArticleWithCommentsResponse.builder()
                .id(dto.id())
                .title(dto.title())
                .content(dto.content())
                .hashtag(dto.hashtag())
                .createdAt(dto.createdAt())
                .email(dto.userAccountDto().email())
                .nickname(nickname)
                .articleCommentsResponse(dto.articleCommentDtos().stream()
                        .map(ArticleCommentResponse::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)))
                .build();
    }

}