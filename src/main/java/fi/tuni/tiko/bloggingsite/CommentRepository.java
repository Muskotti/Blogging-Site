package fi.tuni.tiko.bloggingsite;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Iterable<Comment> findAllByPostId(Long post_id);

    Optional<Comment> findByIdAndAndPostId(Long id, Long post_id);
}
