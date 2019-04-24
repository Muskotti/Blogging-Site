package fi.tuni.tiko.bloggingsite.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fi.tuni.tiko.bloggingsite.blogpost.BlogPost;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * A comment to a blog post.
 *
 * @author Anton Höglund
 */
@Entity
@Table(name = "comments")
public class Comment {

    /**
     * The id of the comment.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The content of the comment.
     */
    private String text;

    /**
     * The blog post the comment is related to.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private BlogPost post;

    /**
     * Constructs a comment with given text and post.
     * @param text the content of the comment.
     * @param post the post the comment is related to.
     */
    public Comment(String text, BlogPost post) {
        this.text = text;
        this.post = post;
    }

    /**
     * Empty constructor, does nothing.
     */
    public Comment() {}

    /**
     * Returns the String content of the comment.
     * @return the String content of the comment.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the String content for the comment.
     * @param text the String content for the comment.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Returns the blog post this comment is related to.
     * @return the blog post this comment is related to.
     */
    public BlogPost getPost() {
        return post;
    }

    /**
     * Sets the blog post this comment is related to.
     * @param post the blog post this comment is related to.
     */
    public void setPost(BlogPost post) {
        this.post = post;
    }

    /**
     * Returns the id of the comment.
     * @return the id of the comment.
     */
    public Long getId() {
        return id;
    }
}
