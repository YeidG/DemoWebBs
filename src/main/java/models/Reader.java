package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Readers")
public class Reader implements Serializable {

    //private static final long serialVersionUID = 961593531578787076L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(mappedBy = "reader")
    private List<BlogsReaders> blogsReadersList;

    public Reader() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<BlogsReaders> getBlogsReadersList() {
        return blogsReadersList;
    }

    public void setBlogsReadersList(List<BlogsReaders> blogsReadersList) {
        this.blogsReadersList = blogsReadersList;
    }
}