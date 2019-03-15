package fi.tuni.tiko.bloggingsite;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
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
		createSampleData();
		printCurlCommands(getCurlCommandJson());
	}

	private void printCurlCommands(JSONArray commandArray) {
		final String ANSI_PURPLE = "\u001B[35m";
		final String ANSI_RESET = "\u001B[0m";
		System.out.println("CURL COMMANDS:");
		for (int i = 0; i < commandArray.length(); i++) {
			JSONObject commandObject = commandArray.getJSONObject(i);
			System.out.println(ANSI_PURPLE + commandObject.getString("name") + ':' + ANSI_RESET);
			System.out.println(commandObject.getString("command"));
		}
	}

	private JSONArray getCurlCommandJson() {
		final String jsonFileName = "curl_commands.json";
		return new JSONArray(new JSONTokener(
				getClass().getClassLoader().getResourceAsStream(jsonFileName)));
	}

	private void createSampleData() {
		postRepository.save(new BlogPost(
				"Test Title",
				"Test Author",
				"Test Blog post",
				new Timestamp(System.currentTimeMillis())));
	}
}
