package fi.tuni.tiko.bloggingsite.blogpost;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * A blog post, the central entity of the backend's purpose.
 *
 * @author Anton Höglund
 */
@Entity
@Table(name = "posts")
public class BlogPost {

    /**
     * The id of blog post.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The title of the blog post.
     */
    private String title;

    /**
     * The author of the blog post.
     */
    private String author;

    /**
     * The content of the blog post.
     */
    private String content;

    /**
     * The time the blog post was created.
     */
    private Timestamp time;

    /**
     * The amount of likes the blog post has.
     */
    private Integer likes = 0;

    /**
     * Empty constructor, does nothing.
     */
    public BlogPost() {
    }

    /**
     * Constructs a blog post.
     * @param title the title of the blog post.
     * @param author the author of the blog post.
     * @param content the content of the blog post.
     * @param time the time the blog post was created.
     */
    public BlogPost(String title, String author, String content, Timestamp time) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.time = time;
    }

    /**
     * Returns the content of the blog post.
     * @return the content of the blog post.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the blog post.
     * @param content the content of the blog post.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Returns the author of the blog post.
     * @return the author of the blog post.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the blog post.
     * @param author the author of the blog post.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Returns the time the blog post was created.
     * @return the time the blog post was created.
     */
    public Timestamp getTime() {
        return time;
    }

    /**
     * Sets the time the blog post was created.
     * @param time the time the blog post was created.
     */
    public void setTime(Timestamp time) {
        this.time = time;
    }

    /**
     * Sets the time the blog post was created.
     * @param millis the time the blog post was created in millisecond format.
     */
    public void setTime(long millis) {
        setTime(new Timestamp(millis));
    }

    /**
     * Returns the id of the blog post.
     * @return the id of the blog post.
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the title of the blog post.
     * @return the title of the blog post.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title for the blog post.
     * @param title the title for the blog post.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the amount of likes in the blog post.
     * @return the amount of likes in the blog post.
     */
    public Integer getLikes() {
        return likes;
    }

    /**
     * Sets the amount of likes in the blog post.
     * @param likes the amount of likes in the blog post.
     */
    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    /**
     * Increments the amount of likes in the blog post.
     */
    public void incrementLikes() {
        likes++;
    }

    /**
     * Decrements the amount of likes in the blog post, but not below zero.
     */
    public void decrementLikes() {
        if (likes > 0) {
            likes--;
        }
    }
}
