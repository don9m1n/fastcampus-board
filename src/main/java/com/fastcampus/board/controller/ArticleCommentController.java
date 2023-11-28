package com.fastcampus.board.controller;

import com.fastcampus.board.dto.UserAccountDto;
import com.fastcampus.board.dto.request.ArticleCommentRequest;
import com.fastcampus.board.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @PostMapping("/new")
    public String postNewArticleComment(ArticleCommentRequest articleCommentRequest) {
        // TODO: 인증 정보 추가
        articleCommentService.saveArticleComment(articleCommentRequest.toDto(
                UserAccountDto.of("uno", "asdf1234", "uno@mail.com", "Uno", "Uno")
        ));

        return "redirect:/articles/" + articleCommentRequest.articleId();
    }

    @PostMapping ("/{commentId}/delete")
    public String deleteArticleComment(@PathVariable Long commentId, Long articleId) {
        articleCommentService.deleteArticleComment(commentId);
        return "redirect:/articles/" + articleId;
    }
}
