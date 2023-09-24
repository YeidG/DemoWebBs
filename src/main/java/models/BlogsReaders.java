package models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Blogs_Readers")
public class BlogsReaders implements Serializable {

    //private static final long serialVersionUID = 7692160202595527714L;

 

    @Id
    @ManyToOne
    @JoinColumn(name = "Readers_ID")
    private Blog blog;

    @Id
    @ManyToOne
    @JoinColumn(name = "Blogs_ID")
    private Reader reader;

    public BlogsReaders() {
    }

    
    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }
}