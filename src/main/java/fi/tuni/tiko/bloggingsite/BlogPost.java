package fi.tuni.tiko.bloggingsite;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class BlogPost {
    @Id
    @GeneratedValue
    private Integer id;

    private String postText;

    private String author;

    private Date date;
}
