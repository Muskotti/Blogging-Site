package fi.tuni.tiko.bloggingsite;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class BlogPost {
    @Id
    @GeneratedValue
    private Integer id;

    private String postText;

    private String author;

    private Timestamp time;

    public BlogPost() {
    }

    public BlogPost(String postText, String author, Timestamp time) {
        this.postText = postText;
        this.author = author;
        this.time = time;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
