package daos;

import models.BlogsReaders;

import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@ViewScoped
public class BlogsReadersDao implements Serializable  {

    private static final long serialVersionUID = -6250137462735843811L;

    @PersistenceContext
    private EntityManager em;

    public void addReaderToBlog(BlogsReaders obj) {
        em.persist(obj);
    }

    public void removeReaderFromBlog(BlogsReaders obj) {
        em.remove(em.merge(obj));
    }
}

