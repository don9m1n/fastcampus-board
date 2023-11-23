package com.fastcampus.board.dto.response;

import com.fastcampus.board.dto.ArticleDto;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
public record ArticleResponse(
        Long id,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String email,
        String nickname
) implements Serializable {

    public static ArticleResponse of(Long id, String title, String content, String hashtag, LocalDateTime createdAt, String email, String nickname) {
        return ArticleResponse.builder()
                .id(id)
                .title(title)
                .content(content)
                .hashtag(hashtag)
                .createdAt(createdAt)
                .email(email)
                .nickname(nickname)
                .build();
    }

    public static ArticleResponse from(ArticleDto dto) {
        String nickname = dto.userAccountDto().nickname();
        if (nickname == null || nickname.isBlank()) {
            nickname = dto.userAccountDto().userId();
        }

        return ArticleResponse.builder()
                .id(dto.id())
                .title(dto.title())
                .content(dto.content())
                .hashtag(dto.hashtag())
                .createdAt(dto.createdAt())
                .email(dto.userAccountDto().email())
                .nickname(nickname)
                .build();
    }

}