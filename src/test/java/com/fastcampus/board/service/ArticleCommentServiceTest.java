package com.fastcampus.board.service;

import com.fastcampus.board.domain.Article;
import com.fastcampus.board.domain.ArticleComment;
import com.fastcampus.board.dto.ArticleCommentDto;
import com.fastcampus.board.repository.ArticleCommentRepository;
import com.fastcampus.board.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks
    private ArticleCommentService sut;

    @Mock
    private ArticleCommentRepository articleCommentRepository;

    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("게시글 ID로 조회하면 해당 게시글의 댓글 리스트를 반환한다.")
    @Test
    void givenArticleId_whenSearchArticleComments_thenReturnArticleComments() {

        Long articleId = 1L;
        given(articleRepository.findById(articleId)).willReturn(
                Optional.of(Article.of("title", "content", "hashtag")));

        List<ArticleCommentDto> articleComments = sut.searchArticleComments(articleId);

        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("댓글 정보를 입력하면 댓글을 저장한다.")
    @Test
    void givenArticleCommentInfo_whenSaveArticleComment_thenSaveArticleComment() {

        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);

        sut.saveArticleComments(ArticleCommentDto.of("comment", LocalDateTime.now(), "deft", LocalDateTime.now(), "deft"));

        then(articleCommentRepository).should().save(any(ArticleComment.class));
    }
}