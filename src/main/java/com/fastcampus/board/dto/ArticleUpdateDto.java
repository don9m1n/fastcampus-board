package com.fastcampus.board.dto;

import lombok.Builder;

/**
 * record는 불편 객체를 좀 더 간단하게 생성할 수 있다.
 * AllArgsConstructor 자동 생성한다.
 * 모든 필드들은 final로 관리되고, getter, toString, equals(), hashcode(), implements Serializable 전부 자동으로 생성된다.
 */
@Builder
public record ArticleUpdateDto(
        String title,
        String content,
        String hashtag
) {

    public static ArticleUpdateDto of(String title, String content, String hashtag) {
        return ArticleUpdateDto.builder()
                .title(title)
                .content(content)
                .hashtag(hashtag)
                .build();
    }

}