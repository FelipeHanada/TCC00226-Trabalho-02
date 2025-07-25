package com.allberfelipe.trabalho_02.service;

import com.allberfelipe.trabalho_02.exception.ArticleNotFoundException;
import com.allberfelipe.trabalho_02.exception.UserNotFoundException;
import com.allberfelipe.trabalho_02.model.Article;
import com.allberfelipe.trabalho_02.model.User;
import com.allberfelipe.trabalho_02.repository.ArticleRepository;
import com.allberfelipe.trabalho_02.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserRepository userRepository;

    public Page<Article> getFavoriteArticles(long userId, Pageable pageable) {
        return articleRepository.findFavoriteArticlesByUserId(userId, pageable);
    }

    public void addFavorite(long userId, long articleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found"));

        user.addFavorite(article);
        userRepository.save(user);
    }

    public void removeFavorite(long userId, long articleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found"));

        user.removeFavorite(article);
        userRepository.save(user);
    }
}
