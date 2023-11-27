package com.fastcampus.board.dto;

import com.fastcampus.board.domain.Article;
import com.fastcampus.board.domain.UserAccount;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * record는 불편 객체를 좀 더 간단하게 생성할 수 있다.
 * AllArgsConstructor 자동 생성한다.
 * 모든 필드들은 final로 관리되고, getter, toString, equals(), hashcode(), implements Serializable 전부 자동으로 생성된다.
 */
@Builder
public record ArticleDto(
        Long id,
        UserAccountDto userAccountDto,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static ArticleDto of(Long id, UserAccountDto userAccountDto, String title, String content, String hashtag, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return ArticleDto.builder()
                .id(id)
                .userAccountDto(userAccountDto)
                .title(title)
                .content(content)
                .hashtag(hashtag)
                .createdAt(createdAt)
                .createdBy(createdBy)
                .modifiedAt(modifiedAt)
                .modifiedBy(modifiedBy)
                .build();
    }

    public static ArticleDto of(UserAccountDto userAccountDto, String title, String content, String hashtag, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return ArticleDto.builder()
                .id(null)
                .userAccountDto(userAccountDto)
                .title(title)
                .content(content)
                .hashtag(hashtag)
                .createdAt(createdAt)
                .createdBy(createdBy)
                .modifiedAt(modifiedAt)
                .modifiedBy(modifiedBy)
                .build();
    }

    public static ArticleDto of(UserAccountDto userAccountDto, String title, String content, String hashtag) {
        return ArticleDto.builder()
                .userAccountDto(userAccountDto)
                .title(title)
                .content(content)
                .hashtag(hashtag)
                .build();
    }

    public static ArticleDto from(Article entity) {
        return ArticleDto.builder()
                .id(entity.getId())
                .userAccountDto(UserAccountDto.from(entity.getUserAccount()))
                .title(entity.getTitle())
                .content(entity.getContent())
                .hashtag(entity.getHashtag())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .modifiedAt(entity.getModifiedAt())
                .modifiedBy(entity.getModifiedBy())
                .build();
    }

    public Article toEntity(UserAccount userAccount) {
        return Article.of(userAccount, title, content, hashtag);
    }

}
