package fi.tuni.tiko.bloggingsite.blogpost;

import fi.tuni.tiko.bloggingsite.LoginController;
import fi.tuni.tiko.bloggingsite.ResourceCreator;
import fi.tuni.tiko.bloggingsite.comment.Comment;
import fi.tuni.tiko.bloggingsite.comment.CommentRepository;
import fi.tuni.tiko.bloggingsite.exceptions.BlogPostIdNotFoundException;
import fi.tuni.tiko.bloggingsite.exceptions.CommentNotFoundException;
import fi.tuni.tiko.bloggingsite.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import static fi.tuni.tiko.bloggingsite.ResourceCreator.createBlogPostResource;
import static fi.tuni.tiko.bloggingsite.ResourceCreator.createCommentResource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {
    @Autowired
    BlogPostRepository postRepository;

    @Autowired
    LoginController loginController;

    @PostMapping("/create")
    public Resource<BlogPost> saveBlogPost(@RequestBody BlogPost post) throws UnauthorizedException {
        if (loginController.userIsAdmin()) {
            BlogPost createdPost = postRepository.save(post);
            Link selfRel = linkTo(methodOn(BlogPostController.class).saveBlogPost(post)).withSelfRel();
            Link createdRel = linkTo(methodOn(BlogPostController.class).findBlogPostById(createdPost.getId())).withRel("createdBlogPost");

            return new Resource<>(createdPost, selfRel, createdRel);
        } else {
            throw new UnauthorizedException();
        }
    }

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

    @GetMapping("/{id}")
    public Resource<BlogPost> findBlogPostById(@PathVariable Long id) throws BlogPostIdNotFoundException {
        Optional<BlogPost> post = postRepository.findById(id);

        if (post.isPresent()) {
            return createBlogPostResource(post.get());
        } else {
            throw new BlogPostIdNotFoundException(id);
        }
    }

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

    @PutMapping("/{id}/like")
    public Resource<BlogPost> likeBlogPostById(@PathVariable Long id) throws BlogPostIdNotFoundException {
        BlogPost post = findBlogPostById(id).getContent();
        post.incrementLikes();
        return createBlogPostResource(postRepository.save(post));
    }

    @PutMapping("/{id}/dislike")
    public Resource<BlogPost> dislikeBlogPostById(@PathVariable Long id) throws BlogPostIdNotFoundException {
        BlogPost post = findBlogPostById(id).getContent();
        post.decrementLikes();
        return createBlogPostResource(postRepository.save(post));
    }

    @DeleteMapping("/{id}/delete")
    public void deleteBlogPostById(@PathVariable Long id) throws BlogPostIdNotFoundException, UnauthorizedException {
        if (loginController.userIsAdmin()) {
            BlogPost post = findBlogPostById(id).getContent();
            postRepository.delete(post);
        } else {
            throw new UnauthorizedException();
        }
    }
}
