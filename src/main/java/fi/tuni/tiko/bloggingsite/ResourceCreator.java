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
    private final static String GET  = "GET";
    private final static String PUT  = "PUT";
    private final static String POST = "POST";
    private final static String DEL  = "DELETE";

    public static Resource<BlogPost> createBlogPostResource(BlogPost post) {
        Link selfRel = linkTo(methodOn(BlogPostController.class).findBlogPostById(post.getId()))
                .withSelfRel()
                .withType(GET);

        Link comments = linkTo(methodOn(CommentController.class).findCommentsByPostId(post.getId()))
                .withRel("comments")
                .withType(GET);

        Link like = linkTo(methodOn(BlogPostController.class).likeBlogPostById(post.getId()))
                .withRel("like")
                .withType(PUT);
        Link dislike = linkTo(methodOn(BlogPostController.class).dislikeBlogPostById(post.getId())).withRel("dislike");
        return new Resource<>(post, selfRel, comments, like, dislike);
    }

    public static Resource<Comment> createCommentResource(Comment comment) {
        Link selfRel = linkTo(methodOn(CommentController.class).
                findCommentsByIdAndByPostId(comment.getId(), comment.getPost().getId())).withSelfRel();
        Link relatedPost = linkTo(methodOn(BlogPostController.class).
                findBlogPostById(comment.getPost().getId())).withRel("relatedPost");
        return new Resource<>(comment, selfRel, relatedPost);
    }
}
