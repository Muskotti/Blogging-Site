package fi.tuni.tiko.bloggingsite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when the client accesses a comment that cannot be found.
 *
 * Exception thrown when the client accesses a comment that cannot be found. Responds to the client with
 * HTTP status 404 (Not found) when thrown.
 *
 * @author Anton Höglund
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommentNotFoundException extends RuntimeException {

    /**
     * Constructs the Exception with a default message referring to the id of the comment not found.
     * @param id the id of the comment.
     */
    public CommentNotFoundException(long id) {
        super("Comment not found by id:" + id);
    }
}
