package services;

import daos.ReaderDao;
import models.Reader;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/readers")
public class ReaderResource {

    private ReaderDao readerDao;

    public ReaderResource() {
        readerDao = new ReaderDao();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Reader getReader(@PathParam("id") Long id) {
        try {
            return readerDao.getReaderById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createReader(Reader reader) {
        try {
            readerDao.createReader(reader);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateReader(@PathParam("id") Long id, Reader reader) {
        try {
            Reader existingReader = readerDao.getReaderById(id);
            if (existingReader != null) {
                reader.setId(id);
                readerDao.updateReader(reader);
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
    public Response deleteReader(@PathParam("id") Long id) {
        try {
            Reader existingReader = readerDao.getReaderById(id);
            if (existingReader != null) {
                readerDao.deleteReader(id);
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

