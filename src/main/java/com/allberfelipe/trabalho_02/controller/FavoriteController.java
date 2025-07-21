package com.allberfelipe.trabalho_02.controller;

import com.allberfelipe.trabalho_02.model.Article;
import com.allberfelipe.trabalho_02.model.PageResult;
import com.allberfelipe.trabalho_02.model.User;
import com.allberfelipe.trabalho_02.service.AuthService;
import com.allberfelipe.trabalho_02.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("article/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private AuthService authService;

    @GetMapping
    public PageResult<Article> getFavoriteArticles(
            @RequestHeader("Authorization") String token,
            @RequestParam(value = "page", defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize
    ) {
        User loggedUser = authService.validateToken(token);

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<Article> page = favoriteService.getFavoriteArticles(loggedUser.getId(), pageable);
        return new PageResult<>(page);
    }

    @PostMapping("{article_id}")
    public void addFavorite(
            @RequestHeader("Authorization") String token,
            @PathVariable("article_id") long articleId
    ) {
        User loggedUser = authService.validateToken(token);

        favoriteService.addFavorite(loggedUser.getId(), articleId);
    }

    @DeleteMapping("{article_id}")
    public void removeFavorite(
            @RequestHeader("Authorization") String token,
            @PathVariable("article_id") long articleId
    ) {
        User loggedUser = authService.validateToken(token);

        favoriteService.removeFavorite(loggedUser.getId(), articleId);
    }
}
