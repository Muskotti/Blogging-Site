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

public class ResourceCreator {
    private final static String GET  = "GET";
    private final static String PUT  = "PUT";
    private final static String POST = "POST";
    private final static String DEL  = "DELETE";

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
                    .withRel("edit")
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

    private static Link[] createLinkArray(Link[] optionalLinks, Link... commonLinks) {
        Link[] combinedLinks = new Link[optionalLinks.length + commonLinks.length];
        int combinedIndex = 0;

        for (int i = 0; i < commonLinks.length; i++) {
            combinedLinks[i] = commonLinks[i];
            combinedIndex++;
        }

        for (int i = 0; i < optionalLinks.length; i++) {
            combinedLinks[combinedIndex++] = optionalLinks[i];
        }

        return combinedLinks;
    }

    private static Link[] optionalLinkArray(Optional<Link>... optionalLinks) {
        ArrayList<Link> links = new ArrayList<>(optionalLinks.length);

        for (Optional<Link> optionalLink : optionalLinks) {
            optionalLink.ifPresent(link -> links.add(link));
        }

        return links.toArray(new Link[0]);
    }
}
