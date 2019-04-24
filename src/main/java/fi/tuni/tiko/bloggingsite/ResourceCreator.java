package fi.tuni.tiko.bloggingsite;

import fi.tuni.tiko.bloggingsite.blogpost.BlogPost;
import fi.tuni.tiko.bloggingsite.blogpost.BlogPostController;
import fi.tuni.tiko.bloggingsite.comment.Comment;
import fi.tuni.tiko.bloggingsite.comment.CommentController;
import fi.tuni.tiko.bloggingsite.exceptions.UnauthorizedException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * A class with generates Resources for RestControllers to return.
 *
 * @author Anton Höglund
 */
public class ResourceCreator {

    /**
     * Constant value for HTTP method GET.
     */
    private final static String GET  = "GET";

    /**
     * Constant value for HTTP method PUT.
     */
    private final static String PUT  = "PUT";

    /**
     * Constant value for HTTP method POST.
     */
    private final static String POST = "POST";

    /**
     * Constant value for HTTP method DELETE.
     */
    private final static String DEL  = "DELETE";

    /**
     * Returns resources for requests to the API root.
     * @return resources for requests to the API root.
     */
    public static Resources<Object> createApiRootResource() {
        Link selfRel = linkTo(methodOn(ApiController.class).getNonInferableLinks())
                .withSelfRel()
                .withType(GET);

        Link getPosts = linkTo(methodOn(BlogPostController.class).findAllBlogPosts())
                .withRel("getPosts")
                .withType(GET);

        Optional<Link> createPost;
        try{
            createPost = Optional.of(linkTo(methodOn(BlogPostController.class).createBlogPost(null))
                    .withRel("createPost")
                    .withType(POST));
        } catch (UnauthorizedException e) {
            createPost = Optional.empty();
        }

        if (createPost.isPresent()) {
            return new Resources<>(Collections.emptySet(), selfRel, getPosts, createPost.get());
        } else {
            return new Resources<>(Collections.emptySet(), selfRel, getPosts);
        }
    }

    /**
     * Returns the resources related to a blog post.
     * @param post the blog post which the resources will be related to.
     * @return the resources related to a blog post.
     */
    public static Resource<BlogPost> createBlogPostResource(BlogPost post) {
        Link selfRel = linkTo(methodOn(BlogPostController.class).findBlogPostById(post.getId()))
                .withSelfRel()
                .withType(GET);

        Link like = linkTo(methodOn(BlogPostController.class).likeBlogPostById(post.getId()))
                .withRel("like")
                .withType(PUT);


        Link dislike = linkTo(methodOn(BlogPostController.class).dislikeBlogPostById(post.getId()))
                .withRel("dislike")
                .withType(PUT);

        Optional<Link> edit;
        try {
            edit = Optional.of(linkTo(methodOn(BlogPostController.class).editBlogPostById(post))
                    .withRel("edit")
                    .withType(PUT));
        } catch (UnauthorizedException e) {
            edit = Optional.empty();
        }

        Optional<Link> delete;
        try {
            delete = Optional.of(linkTo(methodOn(BlogPostController.class).deleteBlogPostById(post.getId()))
                    .withRel("delete")
                    .withType(DEL));
        } catch (UnauthorizedException e) {
            delete = Optional.empty();
        }

        Link comments = linkTo(methodOn(CommentController.class).findCommentsByPostId(post.getId()))
                .withRel("comments")
                .withType(GET);

        Link addComment = linkTo(methodOn(CommentController.class).saveCommentToBlogPostByPostId(
                post.getId(), new Comment("Comment content here", post)))
                .withRel("addComment")
                .withType(POST);

        Link[] links = createLinkArray(optionalLinkArray(edit, delete),
                selfRel, like, dislike, comments, addComment);

        return new Resource<>(post, links);
    }

    /**
     * Returns the resources related to a comment.
     * @param comment the comment the resources will be related to.
     * @return the resources related to a comment.
     */
    public static Resource<Comment> createCommentResource(Comment comment) {
        Link selfRel = linkTo(methodOn(CommentController.class).
                findCommentsByIdAndByPostId(comment.getId(), comment.getPost().getId()))
                .withSelfRel()
                .withType(GET);

        Link relatedPost = linkTo(methodOn(BlogPostController.class).
                findBlogPostById(comment.getPost().getId()))
                .withRel("relatedPost")
                .withType(GET);


        return new Resource<>(comment, selfRel, relatedPost);
    }

    /**
     * Creates a combined array of Links from an array of links and varargs Links
     * @param linkArray the array of links
     * @param variableLengthLinks the varargs Links
     * @return the combined array of Links
     */
    private static Link[] createLinkArray(Link[] linkArray, Link... variableLengthLinks) {
        Link[] combinedLinks = new Link[linkArray.length + variableLengthLinks.length];
        int combinedIndex = 0;

        for (int i = 0; i < variableLengthLinks.length; i++) {
            combinedLinks[i] = variableLengthLinks[i];
            combinedIndex++;
        }

        for (int i = 0; i < linkArray.length; i++) {
            combinedLinks[combinedIndex++] = linkArray[i];
        }

        return combinedLinks;
    }

    /**
     * Creates an array of Links from present Links in Optional Links
     * @param optionalLinks the Optional Links
     * @return the array of Links.
     */
    private static Link[] optionalLinkArray(Optional<Link>... optionalLinks) {
        ArrayList<Link> links = new ArrayList<>(optionalLinks.length);

        for (Optional<Link> optionalLink : optionalLinks) {
            optionalLink.ifPresent(link -> links.add(link));
        }

        return links.toArray(new Link[0]);
    }
}
