package daos;

import models.Blog;
import models.BlogsReaders;
import models.Reader;

import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ViewScoped
public class BlogDao implements Serializable {

    private static final long serialVersionUID = -5850137462735843811L;

    @PersistenceContext(unitName="myPersistenceUnit")
    private final EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager();

    public List<Blog> getAllBlogs(Blog blog) {
        List<Blog> result = new ArrayList<Blog>();
        List<Blog> list = null;

        StringBuffer sql = new StringBuffer();
        Query query = null;
        Map<String, Object> map = new HashMap<String, Object>();

        sql.append("SELECT t FROM ");
        sql.append(Blog.class.getCanonicalName());
        sql.append(" t WHERE 1 = 1 ");

        if (blog.getId() > 0) {
            sql.append(" AND t.id = :id ");
            map.put("id", blog.getId());
        }

        if (blog.getTitle() != null && !blog.getTitle().isEmpty()) {
            sql.append(" AND t.title = :title ");
            map.put("title", blog.getTitle());
        }

        sql.append(" ORDER BY t.id ");
        query = em.createQuery(sql.toString());
        for (Map.Entry<String, Object> valor : map.entrySet()) {
            query.setParameter(valor.getKey(), valor.getValue());
        }

        return query.getResultList();
    }

    public void createBlog(Blog blog) {
        em.getTransaction().begin();
        em.persist(blog);
        em.getTransaction().commit();
    }

    public Blog getBlogById(Long id) {
        return em.find(Blog.class, id);
    }

    public void updateBlog(Blog blog) {
        em.getTransaction().begin();
        em.merge(blog);
        em.getTransaction().commit();
    }

    public void deleteBlog(Long id) {
        em.getTransaction().begin();
        Blog blog = em.find(Blog.class, id);
        if (blog != null) {
            em.remove(blog);
        }
        em.getTransaction().commit();
    }

    public void createBlogReader(Blog blog, Reader reader) {
        em.getTransaction().begin();
        BlogsReaders br = new BlogsReaders();
        br.setB_id(blog.getId());
        br.setR_id(reader.getId());
        em.persist(br);
        em.getTransaction().commit();
    }
}

