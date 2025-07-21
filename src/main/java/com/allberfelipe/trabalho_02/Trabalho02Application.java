package com.allberfelipe.trabalho_02;

import com.allberfelipe.trabalho_02.model.Article;
import com.allberfelipe.trabalho_02.model.ArticleComment;
import com.allberfelipe.trabalho_02.model.User;
import com.allberfelipe.trabalho_02.service.ArticleCommentService;
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
    @Autowired
    private ArticleCommentService articleCommentService;

	public static void main(String[] args) {
		SpringApplication.run(Trabalho02Application.class, args);
	}

	@Override
	public void run(String... args) {
		User user1 = userService.createUser(
				"marco_antonitos@gmail.com",
				"Abc#1234",
				"Marco",
				"Antónitos",
				"Sou Marco.",
				"00123456789"
		);

		User user2 = userService.createUser(
				"joao_vitor@gmail.com",
				"Abc#1234",
				"Joao",
				"Vitor",
				"Sou JV.",
				"00123456789"
		);

		User user3 = userService.createUser(
				"felipehanada@gmail.com",
				"Abc#1234",
				"Felipe",
				"Hanada",
				"about me.",
				"00123456789"
		);

		Article article1 = articleService.createArticle(new Article(
				"Receita de Bolinho de Chuva",
				"Bolinho de chuvaaaaaaa...",
				"https://guiadacozinha.com.br/wp-content/uploads/2022/10/bolinho-de-chuva-tradicional-1.jpg",
				MDFileUtil.readMarkdownFile("bolinho-de-chuva.md"),
				2990,
				LocalDateTime.now(),
				user1
		));

		articleCommentService.createArticleComment(new ArticleComment(
				"Muito bom",
				LocalDateTime.now(),
				user1,
				article1
		));

		articleCommentService.createArticleComment(new ArticleComment(
				"Muito bom!",
				LocalDateTime.now(),
				user2,
				article1
		));

		for (int i=0; i<100; i++) {
			articleCommentService.createArticleComment(new ArticleComment(
					"Muito bom!!!",
					LocalDateTime.now(),
					user3,
					article1
			));
		}

		articleService.createArticle(new Article(
				"Receita de Bolo de Chocolate PREMIUM",
				"É de chocolate PREMIUM.",
				"https://loja.belligourmet.com.br/wp-content/uploads/2022/09/CHP_25017-35.jpg",
				MDFileUtil.readMarkdownFile("bolo-de-chocolate-premium.md"),
				4990,
				LocalDateTime.now(),
				user1
		));

		articleService.createArticle(new Article(
				"Receita de Sopa de Mexilhão",
				"Mexilhão...",
				"https://claudia.abril.com.br/wp-content/uploads/2020/02/receita-sopa-mexilhao-com-erva-doce.jpg?quality=85",
				MDFileUtil.readMarkdownFile("sopa-de-mexilhao.md"),
				12039,
				LocalDateTime.now(),
				user2
		));

		articleService.createArticle(new Article(
				"Sorvete de Maracuja",
				"Muito fácil...",
				"https://melepimenta.com/wp-content/uploads/2015/02/Sorvete-de-maracuja-1.jpg",
				MDFileUtil.readMarkdownFile("sorvete-de-maracuja.md"),
				910,
				LocalDateTime.now(),
				user2
		));

		articleService.createArticle(new Article(
				"Receita Completa de Torta de Morango",
				"A torta de morango é uma sobremesa clássica e deliciosa...",
				"https://tse2.mm.bing.net/th/id/OIP.MM9grg3iJYM-etUenkdzAgHaFj?rs=1&pid=ImgDetMain&o=7&rm=3",
				MDFileUtil.readMarkdownFile("torta-morango.md"),
				12533,
				LocalDateTime.now(),
				user2
		));

		articleService.createArticle(new Article(
				"Receita de Torta de Limão",
				"Torta de limão torta de limão limão...",
				"https://tse3.mm.bing.net/th/id/OIP.b7myN7-zeZUUpZWFqqkRyAAAAA?rs=1&pid=ImgDetMain&o=7&rm=3",
				MDFileUtil.readMarkdownFile("torta-de-limao.md"),
				3366,
				LocalDateTime.now(),
				user2
		));

		articleService.createArticle(new Article(
				"Receita de Maçã do Amor",
				"Maça vermelha...",
				"https://th.bing.com/th/id/OIP.Co9DgyslQf8vwWY_TJSAkQHaFV?w=225&h=180&c=7&r=0&o=7&dpr=1.3&pid=1.7&rm=3",
				MDFileUtil.readMarkdownFile("maca-do-amor.md"),
				4498,
				LocalDateTime.now(),
				user2
		));

		for (int i=0; i<100; i++) {
			articleService.createArticle(new Article(
					"Receita de Maçã do Amor " + i,
					"Maça vermelha...",
					"https://th.bing.com/th/id/OIP.Co9DgyslQf8vwWY_TJSAkQHaFV?w=225&h=180&c=7&r=0&o=7&dpr=1.3&pid=1.7&rm=3",
					MDFileUtil.readMarkdownFile("maca-do-amor.md"),
					4498,
					LocalDateTime.now(),
					user2
			));
		}
	}
}
