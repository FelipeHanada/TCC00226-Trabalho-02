package com.allberfelipe.trabalho_02.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
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

    @ManyToOne
    private User author;

    public Article(String title, String description, String cardImage, String contentMD, User author) {
        this.title = title;
        this.description = description;
        this.cardImage = cardImage;
        this.contentMD = contentMD;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Article{id=" + id + ", title='" + title + "'}";
    }
}
