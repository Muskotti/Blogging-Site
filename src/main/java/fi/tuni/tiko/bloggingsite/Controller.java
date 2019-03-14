package fi.tuni.tiko.bloggingsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

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

    //TODO Create exception if post can't be found by provided id.
    @GetMapping("/posts/{id}")
    public BlogPost findPostById(@PathVariable Long id) {
        return findPostByIdHelper(id);
    }

    public Comment saveCommentToBlogPostByPostId(
            @PathVariable Long id,
            @RequestBody Comment comment) {
        return null;
    }

    private BlogPost findPostByIdHelper(Long id) {
        Optional<BlogPost> post = postRepository.findById(id);

        if (post.isPresent()) {
            return post.get();
        } else {

        }

        return null;
    }
}
