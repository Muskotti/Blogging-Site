package fi.tuni.tiko.bloggingsite;

import fi.tuni.tiko.bloggingsite.blogpost.BlogPost;
import fi.tuni.tiko.bloggingsite.blogpost.BlogPostController;
import fi.tuni.tiko.bloggingsite.comment.Comment;
import fi.tuni.tiko.bloggingsite.comment.CommentController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ResourceCreator {
    public static Resource<BlogPost> createBlogPostResource(BlogPost post) {
        Link selfRel = linkTo(methodOn(BlogPostController.class).findBlogPostById(post.getId())).withSelfRel();
        Link comments = linkTo(methodOn(CommentController.class).findCommentsByPostId(post.getId())).withRel("comments");
        Link like = linkTo(methodOn(BlogPostController.class).likeBlogPostById(post.getId())).withRel("like");
        return new Resource<>(post, selfRel, comments, like);
    }

    public static Resource<Comment> createCommentResource(Comment comment) {
        Link selfRel = linkTo(methodOn(CommentController.class).
                findCommentsByIdAndByPostId(comment.getId(), comment.getPost().getId())).withSelfRel();
        Link relatedPost = linkTo(methodOn(BlogPostController.class).
                findBlogPostById(comment.getPost().getId())).withRel("relatedPost");
        return new Resource<>(comment, selfRel, relatedPost);
    }
}
