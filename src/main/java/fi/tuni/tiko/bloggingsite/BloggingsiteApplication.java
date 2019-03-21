package fi.tuni.tiko.bloggingsite;

import com.fasterxml.jackson.databind.ObjectMapper;
import fi.tuni.tiko.bloggingsite.blogpost.BlogPost;
import fi.tuni.tiko.bloggingsite.blogpost.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BloggingsiteApplication implements CommandLineRunner {

	@Autowired
    BlogPostRepository postRepository;

	@Autowired
	CommentRepository commentRepository;

	public static void main(String[] args) {
		SpringApplication.run(BloggingsiteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createSampleData();

		try {
			printCurlCommands(getCurlCommands());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Curl commands could not be printed");
		}
	}

	private void printCurlCommands(List<CurlCommand> curlCommands) {
		final String ANSI_PURPLE = "\u001B[35m";
		final String ANSI_RESET = "\u001B[0m";
		System.out.println("CURL COMMANDS:");

		for (CurlCommand curlCommand : curlCommands) {
			System.out.println(ANSI_PURPLE + curlCommand.getName() + ANSI_RESET);
			System.out.println(curlCommand.getCommand());
		}
	}

	private List<CurlCommand> getCurlCommands() throws Exception {
		final String resourceFileName = "curl_commands.json";
		InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(resourceFileName);
		return Arrays.asList(new ObjectMapper().readValue(resourceStream, CurlCommand[].class));
	}

	private void createSampleData() {
		BlogPost post1 = postRepository.save(new BlogPost(
				"Test Title",
				"Test Author",
				"Test Blog post",
				new Timestamp(System.currentTimeMillis())));
		Comment comment1 = new Comment();
		comment1.setText("This is a comment");
		comment1.setPost(post1);
		commentRepository.save(comment1);

		postRepository.save(new BlogPost(
				"Kill all humans",
				"Hüman killing guy",
				"We should kill all humans now. Humans have destroyed this planet",
				new Timestamp(System.currentTimeMillis() - 300)));

		postRepository.save(new BlogPost(
				"Respecc erths creaturs",
				"Peace loving hippie",
				"Every person on this earth is as valuable as the next.",
				new Timestamp(System.currentTimeMillis() - 300)));
	}
}
