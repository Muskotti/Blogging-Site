package fi.tuni.tiko.bloggingsite;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Iterable<Comment> findAllByPost(BlogPost post);
}
