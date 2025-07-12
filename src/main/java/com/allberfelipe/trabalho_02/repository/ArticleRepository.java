package com.allberfelipe.trabalho_02.repository;

import com.allberfelipe.trabalho_02.model.Article;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from Article a left outer join fetch a.author where a.id = :id")
    Optional<Article> findByIdWithLock(@Param("id") Long id);

    @Query("select a from Article a left outer join fetch a.author where a.author.id = :author_id")
    List<Article> findAllByAuthorId(@Param("author_id") Long authorId);

    @Query("select a from Article a left outer join fetch a.author where a.author.id = :author_id")
    Page<Article> findAllByAuthorId(@Param("author_id") Long authorId, Pageable pageable);

    @Query("select a from User u join u.favorites a where u.id = :userId")
    Page<Article> findFavoriteArticlesByUserId(@Param("userId") Long userId, Pageable pageable);
}
