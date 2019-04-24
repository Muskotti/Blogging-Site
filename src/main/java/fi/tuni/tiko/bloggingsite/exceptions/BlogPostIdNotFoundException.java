package fi.tuni.tiko.bloggingsite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a client accesses a blog post that cannot be found.
 *
 * Exception thrown when a client accesses a comment that cannot be found. Responds to the client with
 * HTTP status 404 (Not found) when thrown.
 *
 * @author Anton Höglund
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BlogPostIdNotFoundException extends RuntimeException {

    /**
     * Constructs the Exception with a default message referring to the id of the blog post not found.
     * @param id the id of the blog post.
     */
    public BlogPostIdNotFoundException(long id) {
        super("A BlogPost could not be found from the repository with id:" + id);
    }
}
