package fi.tuni.tiko.bloggingsite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends Exception{
    public UnauthorizedException() {
        super("User does not have correct privilege for this request");
    }
}
