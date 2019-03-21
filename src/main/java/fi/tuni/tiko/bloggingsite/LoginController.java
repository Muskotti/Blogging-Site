package fi.tuni.tiko.bloggingsite;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private UserType userType = UserType.ADMIN;

    public UserType getUserType() {
        return userType;
    }
}
