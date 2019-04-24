package fi.tuni.tiko.bloggingsite;

import com.fasterxml.jackson.databind.ObjectMapper;
import fi.tuni.tiko.bloggingsite.blogpost.BlogPost;
import fi.tuni.tiko.bloggingsite.blogpost.BlogPostRepository;
import fi.tuni.tiko.bloggingsite.comment.Comment;
import fi.tuni.tiko.bloggingsite.comment.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

/**
 * The entry point for this blog backend application.
 *
 * @author Anton Höglund
 */
@SpringBootApplication
public class BloggingsiteApplication implements CommandLineRunner {

    /**
     * Injected BlogPostRepository, used for persisting sample data.
     */
	@Autowired
    BlogPostRepository postRepository;

    /**
     * Injected CommentRepository, used for persisting sample data.
     */
	@Autowired
	CommentRepository commentRepository;

    /**
     * The main method of the class, starts the Spring Boot application.
     *
     * @param args command line parameters, not used.
     */
	public static void main(String[] args) {
		SpringApplication.run(BloggingsiteApplication.class, args);
	}

    /**
     * Runs at application startup, creates sample data for the app and prints out curl commands usable in the app.
     *
     * @param args command line parameters, not used.
     */
	@Override
	public void run(String... args) {
		createSampleData();

		try {
			printCurlCommands(getCurlCommands());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Curl commands could not be printed. Look for curl_commands.json in project resources.");
		}
	}

    /**
     * Prints out curl commands usable for the application.
     *
     * @param curlCommands the list of CurlCommand objects to print.
     */
	private void printCurlCommands(List<CurlCommand> curlCommands) {
		final String ANSI_PURPLE = "\u001B[35m";
		final String ANSI_RESET = "\u001B[0m";
		System.out.println("CURL COMMANDS:");

		for (CurlCommand curlCommand : curlCommands) {
			System.out.println(ANSI_PURPLE + curlCommand.getName() + ANSI_RESET);
			System.out.println(curlCommand.getCommand());
		}
	}

    /**
     * Retruns a list of CurlCommand objects containing curl command info.
     * @return Retruns a list of CurlCommand objects containing curl command info.
     * @throws IOException if reading the resource file for the commands was not successful, or if the objects within
     * were not mappable to CurlCommand objects.
     */
	private List<CurlCommand> getCurlCommands() throws IOException {
		final String resourceFileName = "curl_commands.json";
		InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(resourceFileName);
		return Arrays.asList(new ObjectMapper().readValue(resourceStream, CurlCommand[].class));
	}

    /**
     * Creates sample data for the Blog.
     */
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
