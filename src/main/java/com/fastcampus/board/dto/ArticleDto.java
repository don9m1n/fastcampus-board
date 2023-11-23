package com.fastcampus.board.dto;

import lombok.Builder;

import java.time.LocalDateTime;

/**
 * record는 불편 객체를 좀 더 간단하게 생성할 수 있다.
 * AllArgsConstructor 자동 생성한다.
 * 모든 필드들은 final로 관리되고, getter, toString, equals(), hashcode(), implements Serializable 전부 자동으로 생성된다.
 */
@Builder
public record ArticleDto(
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String createdBy
) {

    public static ArticleDto of(String title, String content, String hashtag, LocalDateTime createdAt, String createdBy) {
        return ArticleDto.builder()
                .title(title)
                .content(content)
                .hashtag(hashtag)
                .createdAt(createdAt)
                .createdBy(createdBy)
                .build();
    }

}