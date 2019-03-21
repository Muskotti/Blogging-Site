package fi.tuni.tiko.bloggingsite.comment;

import fi.tuni.tiko.bloggingsite.ResourceCreator;
import fi.tuni.tiko.bloggingsite.blogpost.BlogPost;
import fi.tuni.tiko.bloggingsite.blogpost.BlogPostController;
import fi.tuni.tiko.bloggingsite.exceptions.BlogPostIdNotFoundException;
import fi.tuni.tiko.bloggingsite.exceptions.CommentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static fi.tuni.tiko.bloggingsite.ResourceCreator.createCommentResource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    BlogPostController blogPostController;

    @PostMapping("/posts/{id}/comment")
    public Resource<Comment> saveCommentToBlogPostByPostId(
            @PathVariable Long id,
            @RequestBody Comment comment) throws BlogPostIdNotFoundException {
        BlogPost post = blogPostController.findBlogPostById(id).getContent();
        comment.setPost(post);
        Comment createdComment = commentRepository.save(comment);

        Link selfRel = linkTo(methodOn(CommentController.class).saveCommentToBlogPostByPostId(id, comment)).withSelfRel();

        return new Resource<>(createdComment, selfRel);
    }

    @GetMapping("/posts/{id}/comments")
    public Resources<Resource<Comment>> findCommentsByPostId(@PathVariable Long id) {
        Iterable<Comment> commentIterable = commentRepository.findAllByPostId(id);
        List<Comment> commentList = new ArrayList<>();
        commentIterable.forEach(commentList::add);

        List<Resource<Comment>> commentResources =
                commentList.stream().map(ResourceCreator::createCommentResource).collect(Collectors.toList());
        Link selfRel = linkTo(methodOn(BlogPostController.class).findBlogPostById(id)).withSelfRel();

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
}
