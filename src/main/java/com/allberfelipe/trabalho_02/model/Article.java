package com.allberfelipe.trabalho_02.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "favoritedBy")
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    @Column(columnDefinition = "TINYTEXT")
    private String description;

    @Column(name = "card_image")
    private String cardImage;

    @Lob
    @Column(name = "content_md", columnDefinition = "MEDIUMTEXT")
    private String contentMD;

    private LocalDateTime publishedAt;

    @ManyToOne
    @NotNull
    @JoinColumn(nullable = false)
    private User author;

    @ManyToMany(mappedBy = "favorites")
    @JsonIgnore
    private Set<User> favoritedBy;

    public Article(
            String title,
            String description,
            String cardImage,
            String contentMD,
            LocalDateTime publishedAt,
            User author
    ) {
        this.title = title;
        this.description = description;
        this.cardImage = cardImage;
        this.contentMD = contentMD;
        this.publishedAt = publishedAt;
        this.author = author;
    }
}
