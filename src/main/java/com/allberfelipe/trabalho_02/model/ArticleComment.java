package com.allberfelipe.trabalho_02.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class ArticleComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @JsonIgnore
    private Article article;

    public ArticleComment(
            String content,
            LocalDateTime publishedAt,
            User author,
            Article article
    ) {
        this.content = content;
        this.publishedAt = publishedAt;
        this.author = author;
        this.article = article;
    }
}
