package com.allberfelipe.trabalho_02;

import com.allberfelipe.trabalho_02.model.Article;
import com.allberfelipe.trabalho_02.model.User;
import com.allberfelipe.trabalho_02.repository.ArticleRepository;
import com.allberfelipe.trabalho_02.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Trabalho02Application implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

	public static void main(String[] args) {
		SpringApplication.run(Trabalho02Application.class, args);
	}

	@Override
	public void run(String... args) {

		for (int i=1; i<=100; i++) {
			User user = new User(
					"email" + i + "@domain.br",
					"nome",
					"sobrenome",
					"about_me",
					"00123456789"
			);

			userRepository.save(user);
		}

		User user = new User("email101@domain.br", "nome", "sobrenome", "about_me", "00123456789");
		userRepository.save(user);

		Article article1 = new Article("title1", "description1", "", "conrtent", user);
		articleRepository.save(article1);

		Article article2 = new Article("title2", "description2", "", "conrtent", user);
		articleRepository.save(article2);


		System.out.println(articleRepository.findAllByAuthorId(101L));

//		System.out.println(userRepository.findAll());
	}
}
