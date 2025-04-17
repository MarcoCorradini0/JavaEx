package web.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import data.model.Course;
import data.repository.CourseRepository;
import web.api.model.ErrorMessage;

import java.sql.SQLException;
import java.util.List;

@Path("/api/course")
public class CourseApiResource {
    final CourseRepository courseRepository;

    public CourseApiResource(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> find(@QueryParam("name") String name) {
        List<Course> allCourses;
        if (name == null || name.isEmpty()) {
            allCourses = courseRepository.findAll().list();
        } else {
            allCourses = courseRepository.findByName(name);
        }
        return allCourses;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Course create(Course course) {
        courseRepository.persist(course);
        return course;
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Course course, @PathParam("id") int id) {
        boolean modified = courseRepository.updateCourse(course, id);
        if(modified) {
            return Response.ok(course).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage("Corso con id " + id + " non trovato")).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        courseRepository.deleteById(id);
    }

}
