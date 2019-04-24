package fi.tuni.tiko.bloggingsite;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO javadoc
 */
@RestController
public class LoginController {
    private UserType userType = UserType.ADMIN;

    @PostMapping("/login")
    public ResponseEntity<Void> login() {
        userType = UserType.ADMIN;
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public boolean userIsAdmin() {
        return userType == UserType.ADMIN;
    }
}
