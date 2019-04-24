package fi.tuni.tiko.bloggingsite;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests to login to the application.
 *
 * Handles requests to login to the application. Implementation is just a dummy, it always recognizes
 * the requestor as UserType ADMIN. This class also provides a way for other classes to check the UserType of
 * the client.
 *
 * @author Anton Höglund
 */
@RestController
public class LoginController {

    /**
     * The UserType of the client.
     */
    private UserType userType = UserType.ADMIN;

    /**
     * Handles requests to login.
     * @return a ResponseEntity with a HttpStatus.
     */
    @PostMapping("/login")
    public ResponseEntity<Void> login() {
        userType = UserType.ADMIN;
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Returns true if the client is an admin, false if not.
     * @return true if the client is an admin, false if not.
     */
    public boolean userIsAdmin() {
        return userType == UserType.ADMIN;
    }
}
