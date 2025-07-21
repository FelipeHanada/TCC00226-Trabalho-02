package com.allberfelipe.trabalho_02.service;

import com.allberfelipe.trabalho_02.model.ArticleComment;
import com.allberfelipe.trabalho_02.repository.ArticleCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleCommentService {

    @Autowired
    private ArticleCommentRepository articleCommentRepository;

    public ArticleComment createArticleComment(ArticleComment comment) {
        return articleCommentRepository.save(comment);
    }

    public Page<ArticleComment> getCommentsByArticleId(long articleId, Pageable pageable) {
        return articleCommentRepository.findByArticleId(articleId, pageable);
    }
}
