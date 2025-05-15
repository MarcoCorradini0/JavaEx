package web.api;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.LessonService;
import web.api.model.ErrorMessage;
import web.api.model.LessonCreateRequest;
import web.api.model.LessonResponse;

@Path("/api/lesson")
public class LessonApiResource {
    final LessonService service;

    public LessonApiResource(LessonService service) {
        this.service = service;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LessonResponse> find(@QueryParam("code") Integer courseCode) {
        List<LessonResponse> lessons;
        if (courseCode == null) {
            lessons = service.findAll();
        } else {
            lessons = service.findByCourse(courseCode);
        }
        return lessons;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public LessonResponse create(LessonCreateRequest lesson) {

        return service.persist(lesson);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(LessonCreateRequest lesson, @PathParam("id") int id) {
        try{
            LessonResponse lessonResponse = service.update(lesson, id);

            return Response.ok(lesson).build();
        } catch (EntityNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage("Lezione con id " + id + " non trovata")).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        service.deleteById(id);
    }

}
