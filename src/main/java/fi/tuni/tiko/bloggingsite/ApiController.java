package fi.tuni.tiko.bloggingsite;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for accessing resources at the core of the API.
 *
 * This controller has a single method and mapping for "/api/", at which one can
 * find all the API resources that cannot be inferred from other resources.
 *
 * @author Anton Höglund
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    /**
     * Returns the core API resources that cannot be inferred from other resources.
     *
     * @return the core API resources that cannot be inferred from other resources.
     */
    @GetMapping("/")
    public Resources<Object> getNonInferableLinks() {
        return ResourceCreator.createApiRootResource();
    }
}
