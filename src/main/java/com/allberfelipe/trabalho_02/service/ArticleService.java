package com.allberfelipe.trabalho_02.service;

import com.allberfelipe.trabalho_02.exception.ArticleNotFoundException;
import com.allberfelipe.trabalho_02.model.Article;
import com.allberfelipe.trabalho_02.model.User;
import com.allberfelipe.trabalho_02.repository.ArticleRepository;
import com.allberfelipe.trabalho_02.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getArticles() { return articleRepository.findAll(); }

    public Page<Article> getArticles(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    public Article getArticleById(long articleId) {
        return articleRepository.findById(articleId).orElseThrow(
                () -> new ArticleNotFoundException("Article not found")
        );
    }

    public Page<Article> getArticlesByAuthorId(long authorId, Pageable pageable) {
        return articleRepository.findAllByAuthorId(authorId, pageable);
    }

    public Article createArticle(Article article) {
        article.setPublishedAt(LocalDateTime.now());
        return articleRepository.save(article);
    }

    @Transactional
    public Article updateArticle(Article article) {
        Article existingArticle = articleRepository.findByIdWithLock(article.getId())
                .orElseThrow(() -> new ArticleNotFoundException(
                        "Article id: " + article.getId() + " not found."
                ));

        existingArticle.setTitle(article.getTitle());
        existingArticle.setDescription(article.getDescription());
        existingArticle.setContentMD(article.getContentMD());
        existingArticle.setCardImage(article.getCardImage());
        existingArticle.setPrice(article.getPrice());
    
        return articleRepository.save(existingArticle);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteArticle(long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found"));

        for (User user : article.getFavoritedBy()) {
            user.getFavorites().remove(article);
        }

        articleRepository.delete(article);
    }
}
