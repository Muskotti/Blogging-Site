package fi.tuni.tiko.bloggingsite.exceptions;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(long id) {
        super("Comment not found by id:" + id);
    }
}
