package com.allberfelipe.trabalho_02.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class ArticleComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    @Column(columnDefinition = "TINYTEXT")
    private String content;

    private LocalDateTime publishedAt;

    @ManyToOne
    @NotNull
    @JoinColumn(nullable = false)
    private User author;

    @ManyToOne
    @NotNull
    @JoinColumn(nullable = false)
    private Article article;

    public ArticleComment(
            String title,
            String content,
            LocalDateTime publishedAt,
            User author,
            Article article
    ) {
        this.title = title;
        this.content = content;
        this.publishedAt = publishedAt;
        this.author = author;
        this.article = article;
    }
}
