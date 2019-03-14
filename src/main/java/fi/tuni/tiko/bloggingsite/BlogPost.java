package fi.tuni.tiko.bloggingsite;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class BlogPost {
    @Id
    @GeneratedValue
    private Integer id;

    private String postText;

    private String author;

    private Timestamp date;

    public BlogPost(String postText, String author, Timestamp date) {
        this.postText = postText;
        this.author = author;
        this.date = date;
    }
}
