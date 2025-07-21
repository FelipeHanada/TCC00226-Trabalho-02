package com.allberfelipe.trabalho_02.controller;

import com.allberfelipe.trabalho_02.exception.TokenNotValidException;
import com.allberfelipe.trabalho_02.model.Article;
import com.allberfelipe.trabalho_02.model.ArticleComment;
import com.allberfelipe.trabalho_02.model.PageResult;
import com.allberfelipe.trabalho_02.model.User;
import com.allberfelipe.trabalho_02.service.ArticleCommentService;
import com.allberfelipe.trabalho_02.service.ArticleService;
import com.allberfelipe.trabalho_02.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private AuthService authService;
    @Autowired
    private ArticleCommentService articleCommentService;

    @GetMapping
    public PageResult<Article> getArticles(
            @RequestParam(value = "page", defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize
    ) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<Article> page = articleService.getArticles(pageable);
        return new PageResult<>(page);
    }

    @GetMapping("{article_id}")
    public Article getArticleById(@PathVariable("article_id") long articleId) {
        return articleService.getArticleById(articleId);
    }

    @GetMapping("author/{author_id}")
    public PageResult<Article> getArticlesByAuthorId(
            @PathVariable("author_id") long authorId,
            @RequestParam(value = "page", defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize
    ) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<Article> page = articleService.getArticlesByAuthorId(authorId, pageable);
        return new PageResult<>(page);
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleService.createArticle(article);
    }

    @PatchMapping
    public Article updateArticle(
            @RequestBody Article article
    ) {
        return articleService.updateArticle(article);
    }

    @DeleteMapping("{id}")
    public void deleteArticle(@PathVariable("id") long id) {
        articleService.deleteArticle(id);
    }

    @GetMapping("comment/{id}")
    public PageResult<ArticleComment> getCommentsByArticleId(
            @PathVariable("id") long articleId,
            @RequestParam(value = "page", defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<ArticleComment> page = articleCommentService.getCommentsByArticleId(articleId, pageable);
        return new PageResult<>(page);
    }

    @PostMapping("comment/{id}")
    public ArticleComment createArticleComment(
            @PathVariable("id") long articleId,
            @RequestBody ArticleComment comment,
            @RequestHeader("Authorization") String token
    ) throws TokenNotValidException {
        User user = authService.validateToken(token);
        comment.setAuthor(user);
        Article article = articleService.getArticleById(articleId);
        comment.setArticle(article);
        return articleCommentService.createArticleComment(comment);
    }
}
