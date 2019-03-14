package fi.tuni.tiko.bloggingsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.sql.Timestamp;

@SpringBootApplication
public class BloggingsiteApplication implements CommandLineRunner {

	@Autowired
	BlogPostRepository postRepository;

	public static void main(String[] args) {
		SpringApplication.run(BloggingsiteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		postRepository.save(new BlogPost(
				"Test Title",
				"Test Author",
				"Test Blog post",
				new Timestamp(System.currentTimeMillis())));
	}
}
