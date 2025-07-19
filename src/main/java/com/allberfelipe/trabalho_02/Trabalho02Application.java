package com.allberfelipe.trabalho_02;

import com.allberfelipe.trabalho_02.model.Article;
import com.allberfelipe.trabalho_02.model.User;
import com.allberfelipe.trabalho_02.repository.ArticleRepository;
import com.allberfelipe.trabalho_02.repository.UserRepository;
import com.allberfelipe.trabalho_02.service.ArticleService;
import com.allberfelipe.trabalho_02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

		for (int i=1; i<=10; i++) {
			userService.createUser(
					"email" + i + "@domain.br",
					"1234",
					"nome",
					"sobrenome",
					"about_me",
					"00123456789"
			);
		}

		User user = userService.createUser(
				"email101@domain.br",
				"1234",
				"nome",
				"sobrenome",
				"about_me",
				"00123456789"
		);

		Article article1 = new Article("title1", "description1", "", "conrtent", user);
		articleService.createArticle(article1);

		Article article2 = new Article("title2", "description2", "", "conrtent", user);
		articleService.createArticle(article2);
	}
}
