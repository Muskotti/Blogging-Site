package fi.tuni.tiko.bloggingsite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a client accesses a resource without proper authorization.
 *
 * Exception thrown when a client accesses a resource without proper authorization. Responds to the client with
 * HTTP status 401 (Unauthorized) when thrown.
 *
 * @author Anton Höglund
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends Exception{

    /**
     * Constructs the Exception with a default message.
     */
    public UnauthorizedException() {
        super("User does not have correct privilege for this request");
    }
}
