package fi.tuni.tiko.bloggingsite.blogpost;

import fi.tuni.tiko.bloggingsite.LoginController;
import fi.tuni.tiko.bloggingsite.ResourceCreator;
import fi.tuni.tiko.bloggingsite.exceptions.BlogPostIdNotFoundException;
import fi.tuni.tiko.bloggingsite.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static fi.tuni.tiko.bloggingsite.ResourceCreator.createBlogPostResource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * An interface for clients to perform CRUD operations related to blog posts.
 *
 * @author Anton Höglund
 */
@RestController
@RequestMapping("/api/posts")
public class BlogPostController {

    /**
     * Injected BlogPostRepository used to perform CRUD operations to blog posts.
     */
    @Autowired
    BlogPostRepository postRepository;

    /**
     * Injected LoginController used to verify the client's privilege.
     */
    @Autowired
    LoginController loginController;

    /**
     * Creates a blog post.
     * @param post the blog post to create.
     * @return an echo of the blog post resource created.
     * @throws UnauthorizedException if the client was not authorized to perform this action.
     */
    @PostMapping("/create")
    public Resource<BlogPost> createBlogPost(@RequestBody BlogPost post) throws UnauthorizedException {
        if (loginController.userIsAdmin()) {
            BlogPost createdPost = postRepository.save(post);
            Link selfRel = linkTo(methodOn(BlogPostController.class).createBlogPost(post)).withSelfRel();
            Link createdRel = linkTo(methodOn(BlogPostController.class).findBlogPostById(createdPost.getId())).withRel("createdBlogPost");

            return new Resource<>(createdPost, selfRel, createdRel);
        } else {
            throw new UnauthorizedException();
        }
    }

    /**
     * Returns all blog post resources.
     * @return all blog post resources.
     */
    @GetMapping("/")
    public Resources<Resource<BlogPost>> findAllBlogPosts() {
        Iterable<BlogPost> postIterable = postRepository.findAll();
        List<BlogPost> postList = new ArrayList<>();
        postIterable.forEach(postList::add);

        List<Resource<BlogPost>> postResources =
                postList.stream().map(ResourceCreator::createBlogPostResource).collect(Collectors.toList());
        Link selfRel = linkTo(methodOn(BlogPostController.class).findAllBlogPosts()).withSelfRel();

        return new Resources<>(postResources, selfRel);
    }

    /**
     * Returns a blog post by id.
     * @param id the if of the blog post.
     * @return the blog post.
     * @throws BlogPostIdNotFoundException if the blog post was not found.
     */
    @GetMapping("/{id}")
    public Resource<BlogPost> findBlogPostById(@PathVariable Long id) throws BlogPostIdNotFoundException {
        Optional<BlogPost> post = postRepository.findById(id);

        if (post.isPresent()) {
            return createBlogPostResource(post.get());
        } else {
            throw new BlogPostIdNotFoundException(id);
        }
    }

    /**
     * Modifies an already existing blog post, identified by the id in the edited version.
     * @param edited the edited version of the blog post.
     * @return the edited blog post.
     * @throws BlogPostIdNotFoundException if the id in the edited blog post was not found.
     * @throws UnauthorizedException if the client was not authorized to perform this action.
     */
    @PutMapping("/edit")
    public Resource<BlogPost> editBlogPostById(@RequestBody BlogPost edited)
            throws BlogPostIdNotFoundException, UnauthorizedException {
        if (loginController.userIsAdmin()) {
            BlogPost original = findBlogPostById(edited.getId()).getContent();
            edited.setLikes(original.getLikes());
            edited = postRepository.save(edited);

            return createBlogPostResource(edited);
        } else {
            throw new UnauthorizedException();
        }
    }

    /**
     * Adds a like to the blog post.
     * @param id the id of the blog post.
     * @return the blog post that was liked.
     * @throws BlogPostIdNotFoundException if the blog post was not found by provided id.
     */
    @PutMapping("/{id}/like")
    public Resource<BlogPost> likeBlogPostById(@PathVariable Long id) throws BlogPostIdNotFoundException {
        BlogPost post = findBlogPostById(id).getContent();
        post.incrementLikes();
        return createBlogPostResource(postRepository.save(post));
    }

    /**
     * Removes a like from the blog post.
     * @param id the id of the blog post.
     * @return the blog post that was liked.
     * @throws BlogPostIdNotFoundException if the blog post was not found by provided id.
     */
    @PutMapping("/{id}/dislike")
    public Resource<BlogPost> dislikeBlogPostById(@PathVariable Long id) throws BlogPostIdNotFoundException {
        BlogPost post = findBlogPostById(id).getContent();
        post.decrementLikes();
        return createBlogPostResource(postRepository.save(post));
    }

    /**
     * Deletes a blog post.
     * @param id the id of the blog post.
     * @return a ResponseEntity confirming action success.
     * @throws BlogPostIdNotFoundException if a blog post was not found by provided id.
     * @throws UnauthorizedException if the client was not authorized to perform this action.
     */
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteBlogPostById(@PathVariable Long id)
            throws BlogPostIdNotFoundException, UnauthorizedException {
        if (loginController.userIsAdmin()) {
            BlogPost post = findBlogPostById(id).getContent();
            postRepository.delete(post);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new UnauthorizedException();
        }
    }
}
