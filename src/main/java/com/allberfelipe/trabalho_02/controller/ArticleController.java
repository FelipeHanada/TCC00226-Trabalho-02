package com.allberfelipe.trabalho_02.controller;

import com.allberfelipe.trabalho_02.exception.TokenNotValidException;
import com.allberfelipe.trabalho_02.model.Article;
import com.allberfelipe.trabalho_02.model.PageResult;
import com.allberfelipe.trabalho_02.model.User;
import com.allberfelipe.trabalho_02.service.ArticleService;
import com.allberfelipe.trabalho_02.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private AuthService authService;

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
    public void deleteUser(@PathVariable("id") long id) {
        articleService.deleteArticle(id);
    }

    @GetMapping("favorite")
    public PageResult<Article> getFavoriteArticles(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "page", defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize
    ) {
        User loggedUser = authService.getUserByToken(token)
                .orElseThrow(() -> new TokenNotValidException("Token not found"));

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<Article> page = articleService.getFavoriteArticles(loggedUser.getId(), pageable);
        return new PageResult<>(page);
    }

    @PostMapping("favorite/{article_id}")
    public void addFavorite(
            @PathVariable("article_id") long articleId,
            @RequestParam(value = "token") String token
    ) {
        User loggedUser = authService.getUserByToken(token)
                .orElseThrow(() -> new TokenNotValidException("Token not found"));

        articleService.addFavorite(loggedUser.getId(), articleId);
    }

    @DeleteMapping("favorite/remove/{article_id}")
    public void removeFavorite(
            @PathVariable("article_id") long articleId,
            @RequestParam(value = "token") String token
    ) {
        User loggedUser = authService.getUserByToken(token)
                .orElseThrow(() -> new TokenNotValidException("Token not found"));

        articleService.removeFavorite(loggedUser.getId(), articleId);
    }
}
