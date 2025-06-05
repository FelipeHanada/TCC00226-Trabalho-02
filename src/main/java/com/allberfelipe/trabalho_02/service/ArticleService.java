package com.allberfelipe.trabalho_02.service;

import com.allberfelipe.trabalho_02.model.Article;
import com.allberfelipe.trabalho_02.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getArticles() { return articleRepository.findAll(); }

    public Page<Article> getArticles(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    public Page<Article> getArticlesByAuthorId(long authorId, Pageable pageable) {
        return articleRepository.findAllByAuthorId(authorId, pageable);
    }

    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }
}
