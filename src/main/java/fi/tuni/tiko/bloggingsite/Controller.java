package fi.tuni.tiko.bloggingsite;

import fi.tuni.tiko.bloggingsite.exceptions.BlogPostIdNotFoundException;
import fi.tuni.tiko.bloggingsite.exceptions.CommentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Resource<BlogPost> saveBlogPost(@RequestBody BlogPost post) {
        BlogPost createdPost = postRepository.save(post);
        Link selfRel = linkTo(methodOn(Controller.class).saveBlogPost(post)).withSelfRel();
        Link createdRel = linkTo(methodOn(Controller.class).findBlogPostById(createdPost.getId())).withRel("createdBlogPost");

        return new Resource<>(createdPost, selfRel, createdRel);
    }

    @GetMapping("/posts")
    public Resources<Resource<BlogPost>> findAllBlogPosts() {
        Iterable<BlogPost> postIterable = postRepository.findAll();
        List<BlogPost> postList = new ArrayList<>();
        postIterable.forEach(postList::add);

        List<Resource<BlogPost>> postResources =
                postList.stream().map(Controller::createBlogPostResource).collect(Collectors.toList());
        Link selfRel = linkTo(methodOn(Controller.class).findAllBlogPosts()).withSelfRel();

        return new Resources<>(postResources, selfRel);
    }

    @GetMapping("/posts/{id}")
    public Resource<BlogPost> findBlogPostById(@PathVariable Long id) throws BlogPostIdNotFoundException {
        Optional<BlogPost> post = postRepository.findById(id);

        if (post.isPresent()) {
            return createBlogPostResource(post.get());
        } else {
            throw new BlogPostIdNotFoundException(id);
        }
    }

    @PostMapping("/posts/{id}/comment")
    public Resource<Comment> saveCommentToBlogPostByPostId(
            @PathVariable Long id,
            @RequestBody Comment comment) throws BlogPostIdNotFoundException {
        BlogPost post = findBlogPostById(id).getContent();
        comment.setPost(post);
        Comment createdComment = commentRepository.save(comment);

        Link selfRel = linkTo(methodOn(Controller.class).saveCommentToBlogPostByPostId(id, comment)).withSelfRel();

        return new Resource<>(createdComment, selfRel);
    }

    @GetMapping("/posts/{id}/comments")
    public Resources<Resource<Comment>> findCommentsByPostId(@PathVariable Long id) {
        Iterable<Comment> commentIterable = commentRepository.findAllByPostId(id);
        List<Comment> commentList = new ArrayList<>();
        commentIterable.forEach(commentList::add);

        List<Resource<Comment>> commentResources =
                commentList.stream().map(Controller::createCommentResource).collect(Collectors.toList());
        Link selfRel = linkTo(methodOn(Controller.class).findBlogPostById(id)).withSelfRel();

        return new Resources<>(commentResources, selfRel);
    }

    @GetMapping("posts/{postId}/comments/{commentId}")
    public Resource<Comment> findCommentsByIdAndByPostId(@PathVariable Long postId, @PathVariable Long commentId) {
        Optional<Comment> comment = commentRepository.findByIdAndAndPostId(commentId, postId);

        if (comment.isPresent()) {
            return createCommentResource(comment.get());
        } else {
            throw new CommentNotFoundException(commentId);
        }
    }

    private static Resource<BlogPost> createBlogPostResource(BlogPost post) {
        Link postLink = linkTo(methodOn(Controller.class).findBlogPostById(post.getId())).withSelfRel();
        return new Resource<>(post, postLink);
    }

    private static Resource<Comment> createCommentResource(Comment comment) {
        Link selfRel = linkTo(methodOn(Controller.class).
                findCommentsByIdAndByPostId(comment.getId(), comment.getPost().getId())).withSelfRel();
        Link relatedPost = linkTo(methodOn(Controller.class).
                findBlogPostById(comment.getPost().getId())).withRel("relatedPost");
        return new Resource<>(comment, selfRel, relatedPost);
    }
}
