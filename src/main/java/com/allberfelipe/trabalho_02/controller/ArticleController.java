package com.allberfelipe.trabalho_02.controller;

import com.allberfelipe.trabalho_02.model.Article;
import com.allberfelipe.trabalho_02.model.PageResult;
import com.allberfelipe.trabalho_02.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public PageResult<Article> getArticles(
            @RequestParam(value = "page", defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize
    ) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<Article> page = articleService.getArticles(pageable);
        return new PageResult<>(page);
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
    public Article createArticle(Article article) {
        return articleService.createArticle(article);
    }
}
