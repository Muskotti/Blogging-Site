package fi.tuni.tiko.bloggingsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class Controller {
    @Autowired
    BlogPostRepository postRepository;

    @GetMapping("/hello")
    public String hello() {
        return "Hello, the time at the server is now " + new Date() + "\n";
    }

    @PostMapping("/post")
    public BlogPost saveBlogPost(@RequestBody BlogPost post) {
        return postRepository.save(post);
    }

    @GetMapping("/posts")
    public Iterable<BlogPost> findAllBlogPosts() {
        return postRepository.findAll();
    }
}
