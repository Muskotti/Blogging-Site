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

    public BlogPost(String postText, String author, Timestamp time) {
        this.postText = postText;
        this.author = author;
        this.time = time;
    }
}
