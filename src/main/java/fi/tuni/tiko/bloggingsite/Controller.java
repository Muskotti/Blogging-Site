package fi.tuni.tiko.bloggingsite;

import fi.tuni.tiko.bloggingsite.exceptions.BlogPostIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    BlogPostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

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

    @GetMapping("/posts/{id}")
    public BlogPost findPostById(@PathVariable Long id) throws BlogPostIdNotFoundException {
        return findPostByIdHelper(id);
    }

    @PostMapping("/posts/{id}/comment")
    public Comment saveCommentToBlogPostByPostId(
            @PathVariable Long id,
            @RequestBody Comment comment) throws BlogPostIdNotFoundException {
        BlogPost post = findPostByIdHelper(id);
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    @GetMapping("/posts/{id}/comments")
    public Iterable<Comment> findCommentsByPostId(@PathVariable Long id) {
        return commentRepository.findAllByPostId(id);
    }

    private BlogPost findPostByIdHelper(Long id) throws BlogPostIdNotFoundException{
        Optional<BlogPost> post = postRepository.findById(id);

        if (post.isPresent()) {
            return post.get();
        } else {
            throw new BlogPostIdNotFoundException(id);
        }
    }
}
