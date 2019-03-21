package fi.tuni.tiko.bloggingsite.blogpost;

import org.springframework.data.repository.CrudRepository;

public interface BlogPostRepository extends CrudRepository<BlogPost, Long> {
}
