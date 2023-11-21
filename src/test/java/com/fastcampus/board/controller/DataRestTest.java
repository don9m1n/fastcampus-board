package com.fastcampus.board.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Data Rest 테스트 - API")
@SpringBootTest
@Transactional
@AutoConfigureMockMvc // MockMvc를 사용하기 위함.
public class DataRestTest {

    private final MockMvc mvc;

    public DataRestTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[API] 게시글 리스트 조회 ")
    @Test
    void givenNothing_whenRequestArticles_thenReturnArticlesJsonResponse() throws Exception {

        mvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("[API] 게시글 단건 조회 ")
    @Test
    void givenArticleId_whenRequestArticle_thenReturnArticleJsonResponse() throws Exception {

        mvc.perform(get("/api/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("[API] 게시글 -> 댓글 리스트 조회 ")
    @Test
    void givenArticleId_whenRequestArticleCommentsFromArticles_thenReturnArticleCommentsJsonResponse() throws Exception {

        mvc.perform(get("/api/articles/1/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("[API] 댓글 리스트 조회 ")
    @Test
    void givenNothing_whenRequestArticleComments_thenReturnArticleCommentsJsonResponse() throws Exception {

        mvc.perform(get("/api/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("[API] 댓글 단건 조회 ")
    @Test
    void givenArticleCommentId_whenRequestArticleComment_thenReturnArticleCommentJsonResponse() throws Exception {

        mvc.perform(get("/api/articleComments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }
}
