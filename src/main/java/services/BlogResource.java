package services;

import daos.BlogDao;
import models.Blog;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/blogs")
public class BlogResource {

    private BlogDao blogDao;

    public BlogResource() {
        blogDao = new BlogDao();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Blog> getAllUserBlogs(@PathParam("id") Long id) {
        try {
            Blog blog = new Blog();
            blog.setId(id);
            return blogDao.getAllBlogs(blog);
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBlog(Blog blog) {
        try {
            blogDao.createBlog(blog);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Blog getBlog(@PathParam("id") Long id) {
        try {
            return blogDao.getBlogById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBlog(@PathParam("id") Long id, Blog blog) {
        try {
            Blog existingBlog = blogDao.getBlogById(id);
            if (existingBlog != null) {
                blog.setId(id);
                blogDao.updateBlog(blog);
                return Response.status(Response.Status.OK).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBlog(@PathParam("id") Long id) {
        try {
            Blog existingBlog = blogDao.getBlogById(id);
            if (existingBlog != null) {
                blogDao.deleteBlog(id);
                return Response.status(Response.Status.OK).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
