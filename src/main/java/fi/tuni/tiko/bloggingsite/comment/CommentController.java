package fi.tuni.tiko.bloggingsite.comment;

import fi.tuni.tiko.bloggingsite.LoginController;
import fi.tuni.tiko.bloggingsite.ResourceCreator;
import fi.tuni.tiko.bloggingsite.blogpost.BlogPost;
import fi.tuni.tiko.bloggingsite.blogpost.BlogPostController;
import fi.tuni.tiko.bloggingsite.exceptions.BlogPostIdNotFoundException;
import fi.tuni.tiko.bloggingsite.exceptions.CommentNotFoundException;
import fi.tuni.tiko.bloggingsite.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static fi.tuni.tiko.bloggingsite.ResourceCreator.createCommentResource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Rest controller which allows a client to find, save, and delete comments.
 *
 * @author Anton Höglund
 */
@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    /**
     * The injected CommentRepository used for performing CRUD operations in this controller.
     */
    @Autowired
    CommentRepository commentRepository;

    /**
     * The injected BlogPostController used for finding the blog posts the comments are related to.
     */
    @Autowired
    BlogPostController blogPostController;

    /**
     * The login controller used to check the client's privilege.
     */
    @Autowired
    LoginController loginController;


    /**
     * Creates a comment to a blog post.
     * @param id the id of the blog post the comment will be related to.
     * @param comment the comment itself.
     * @return an echo of the created comment resource.
     * @throws BlogPostIdNotFoundException if a blog post could not be found with given id.
     */
    @PostMapping("/create")
    public Resource<Comment> saveCommentToBlogPostByPostId(
            @PathVariable(name = "postId") Long id,
            @RequestBody Comment comment) throws BlogPostIdNotFoundException {
        BlogPost post = blogPostController.findBlogPostById(id).getContent();
        comment.setPost(post);
        Comment createdComment = commentRepository.save(comment);

        Link selfRel = linkTo(methodOn(CommentController.class).saveCommentToBlogPostByPostId(id, comment)).withSelfRel();

        return new Resource<>(createdComment, selfRel);
    }

    /**
     * Returs comment resources by blog post id.
     * @param id the id of the blog post.
     * @return the comment resources.
     */
    @GetMapping("/")
    public Resources<Resource<Comment>> findCommentsByPostId(
            @PathVariable(name = "postId") Long id) {
        Iterable<Comment> commentIterable = commentRepository.findAllByPostId(id);
        List<Comment> commentList = new ArrayList<>();
        commentIterable.forEach(commentList::add);

        List<Resource<Comment>> commentResources =
                commentList.stream().map(ResourceCreator::createCommentResource).collect(Collectors.toList());
        Link selfRel = linkTo(methodOn(BlogPostController.class).findBlogPostById(id)).withSelfRel();

        return new Resources<>(commentResources, selfRel);
    }

    /**
     * Returns a comment by the id of the blog post it's related to, and the id of the comment itself.
     * @param postId the if of the blog post.
     * @param commentId the id of the comment.
     * @return the comment resource.
     * @throws CommentNotFoundException if the Comment could not be found with given ids.
     */
    @GetMapping("/{commentId}")
    public Resource<Comment> findCommentsByIdAndByPostId(
            @PathVariable(name = "postId") Long postId,
            @PathVariable(name = "commentId") Long commentId) throws CommentNotFoundException {
        Optional<Comment> comment = commentRepository.findByIdAndAndPostId(commentId, postId);

        if (comment.isPresent()) {
            return createCommentResource(comment.get());
        } else {
            throw new CommentNotFoundException(commentId);
        }
    }

    /**
     * Deletes a comment by it's blog post id and comment id.
     * @param postId the blog post id.
     * @param commentId the comment id.
     * @throws UnauthorizedException if the client was not authorized to perform this action.
     * @throws CommentNotFoundException if the comment to delete could not be found with given ids.
     */
    @DeleteMapping("/{commentId}/delete")
    public void deleteCommentById(
            @PathVariable(name = "postId") Long postId,
            @PathVariable(name = "commentId") Long commentId)
            throws UnauthorizedException, CommentNotFoundException {
        if (loginController.userIsAdmin()) {
            Comment deletable = findCommentsByIdAndByPostId(postId, commentId).getContent();
            commentRepository.delete(deletable);
        } else {
            throw new UnauthorizedException();
        }
    }
}
