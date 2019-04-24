package fi.tuni.tiko.bloggingsite.blogpost;

import org.springframework.data.repository.CrudRepository;

/**
 * Simple CRUD repository for blog posts.
 */
public interface BlogPostRepository extends CrudRepository<BlogPost, Long> {
}
