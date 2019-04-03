package fi.tuni.tiko.bloggingsite;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ApiController {
    @GetMapping("/")
    public Resources<Object> getNonInferableLinks() {
        return ResourceCreator.createApiRootResource();
    }
}
