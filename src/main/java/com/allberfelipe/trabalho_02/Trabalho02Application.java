package com.allberfelipe.trabalho_02;

import com.allberfelipe.trabalho_02.model.Article;
import com.allberfelipe.trabalho_02.model.User;
import com.allberfelipe.trabalho_02.service.ArticleService;
import com.allberfelipe.trabalho_02.service.UserService;
import com.allberfelipe.trabalho_02.util.MDFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;


@SpringBootApplication
public class Trabalho02Application implements CommandLineRunner {

	@Autowired
	private UserService userService;

    @Autowired
    private ArticleService articleService;

	public static void main(String[] args) {
		SpringApplication.run(Trabalho02Application.class, args);
	}

	@Override
	public void run(String... args) {
		User user = userService.createUser(
				"marco_antonitos@gmail.com",
				"senha",
				"Marco",
				"Antónitos",
				"Sou Marco.",
				"00123456789"
		);

		String markdownContent = MDFileUtil.readMarkdownFile("torta-morango.md");
		Article article1 = new Article(
				"Receita Completa de Torta de Morango",
				"A torta de morango é uma sobremesa clássica e deliciosa...",
				"https://tse2.mm.bing.net/th/id/OIP.MM9grg3iJYM-etUenkdzAgHaFj?rs=1&pid=ImgDetMain&o=7&rm=3",
				markdownContent,
				LocalDateTime.now(),
				user
		);
		articleService.createArticle(article1);
	}
}
