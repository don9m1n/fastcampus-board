package com.fastcampus.board.service;

import com.fastcampus.board.domain.Article;
import com.fastcampus.board.domain.type.SearchType;
import com.fastcampus.board.dto.ArticleDto;
import com.fastcampus.board.dto.ArticleWithCommentsDto;
import com.fastcampus.board.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;


    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        if (searchKeyword == null || searchKeyword.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }

        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
            case ID -> articleRepository.findByUserAccount_userIdContaining(searchKeyword, pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from);
            // TODO: 해시 태그 검색은 앞에 #을 붙이는 방식으로 구현함. 추후 개선 해야하는 부분
            case HASHTAG -> articleRepository.findByHashtag("#" + searchKeyword, pageable).map(ArticleDto::from);
        };
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticle(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다. - articleId: " + articleId));
    }

    public void saveArticle(ArticleDto dto) {
        articleRepository.save(dto.toEntity());
    }

    public void updateArticle(ArticleDto dto) {
        try {
            Article article = articleRepository.getReferenceById(dto.id());
            if(dto.title() != null){
                article.setTitle(dto.title());
            }
            if (dto.content() != null) {
                article.setContent(dto.content());
            }

            article.setHashtag(dto.hashtag());
            articleRepository.save(article);
        } catch (EntityNotFoundException e) {
            log.warn("게시글 업데이트 실패. 게시글을 찾을 수 없습니다. - dto: {}", dto);
        }
    }

    public void deleteArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }
}
