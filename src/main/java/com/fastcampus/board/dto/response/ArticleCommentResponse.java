package com.fastcampus.board.dto.response;

import com.fastcampus.board.dto.ArticleCommentDto;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
public record ArticleCommentResponse(
        Long id,
        String content,
        LocalDateTime createdAt,
        String email,
        String nickname
) implements Serializable {

    public static ArticleCommentResponse of(Long id, String content, LocalDateTime createdAt, String email, String nickname) {
        return ArticleCommentResponse.builder()
                .id(id)
                .content(content)
                .createdAt(createdAt)
                .email(email)
                .nickname(nickname)
                .build();
    }

    public static ArticleCommentResponse from(ArticleCommentDto dto) {
        String nickname = dto.userAccountDto().nickname();
        if (nickname == null || nickname.isBlank()) {
            nickname = dto.userAccountDto().userId();
        }

        return ArticleCommentResponse.builder()
                .id(dto.id())
                .content(dto.content())
                .createdAt(dto.createdAt())
                .email(dto.userAccountDto().email())
                .nickname(nickname)
                .build();
    }

}