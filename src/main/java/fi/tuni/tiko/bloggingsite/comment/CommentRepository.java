package fi.tuni.tiko.bloggingsite.comment;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * CRUD repository for Comments.
 *
 * @author Anton Höglund
 */
public interface CommentRepository extends CrudRepository<Comment, Long> {

    /**
     * Finds Iterable of Comments by blog post id.
     * @param post_id the id of the blog post.
     * @return the Iterable of Comments.
     */
    Iterable<Comment> findAllByPostId(Long post_id);

    /**
     * Finds a single comment by it's id and the id of the blog post it's related to.
     * @param id the id of the comment.
     * @param post_id the id of the blog post.
     * @return the single comment in Optional wrapper.
     */
    Optional<Comment> findByIdAndAndPostId(Long id, Long post_id);
}
