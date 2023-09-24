package beans;

import daos.BlogDao;
import models.Blog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

@Named
@ApplicationScoped
public class BlogBean implements Serializable  {

    private static final long serialVersionUID = 1376319740144826761L;

    @Inject
    private BlogDao dao;

    @Inject
    private ReaderBean readerBean;

    private Blog queryObject;

    private Blog object;

    private ArrayList<Blog> blogs;

    public BlogBean() {
        queryObject = new Blog();
        object = new Blog();
    }

    public String buscarBlogs() {
        try {
            this.blogs = (ArrayList<Blog>) dao.getAllBlogs(queryObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String enviarEditar(Long id) {
        try {
            this.object = id != null ? this.blogs.stream()
                    .filter(blog -> blog.getId() == id)
                    .findFirst()
                    .orElse(null)
                    : new Blog();

            return "editarBlog";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String regresarConsulta() {
        try {
            return "consultarBlog";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String actualizarBlog() {
        try {
            if(this.object.getId() > 0){
                dao.updateBlog(this.object);
            }
            else {
                //dao.createBlogReader();
                dao.createBlog(this.object);
            }

            this.buscarBlogs();
            return regresarConsulta();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String borrarBlog(Long id) {
        try {
            dao.deleteBlog(id);

            this.buscarBlogs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void limpiarForm() {
        this.queryObject = new Blog();
        this.blogs = new ArrayList<>();
    }


    public Blog getQueryObject() {
        return queryObject;
    }

    public void setQueryObject(Blog queryObject) {
        this.queryObject = queryObject;
    }

    public ArrayList<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(ArrayList<Blog> blogs) {
        this.blogs = blogs;
    }

    public void setDao(BlogDao dao) {
        this.dao = dao;
    }

    public Blog getObject() {
        return object;
    }

    public void setObject(Blog object) {
        this.object = object;
    }
}
