package com.allberfelipe.trabalho_02.repository;

import com.allberfelipe.trabalho_02.model.ArticleComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
    Page<ArticleComment> findByArticleId(long articleId, Pageable pageable);
}
