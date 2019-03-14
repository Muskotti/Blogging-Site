package fi.tuni.tiko.bloggingsite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BlogPostIdNotFoundException extends RuntimeException {
    public BlogPostIdNotFoundException(long id) {
        super("A BlogPost could not be found from the repository with id:" + id);
    }

    public BlogPostIdNotFoundException() {
        super();
    }

    public BlogPostIdNotFoundException(String message) {
        super(message);
    }

    public BlogPostIdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlogPostIdNotFoundException(Throwable cause) {
        super(cause);
    }
}
