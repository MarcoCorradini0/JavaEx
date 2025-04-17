package web;

import data.model.Course;
import data.repository.CourseRepository;
import io.quarkus.qute.Template;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/")
public class HomeResource {

    private final Template home;
    private final CourseRepository courseRepository;

    public HomeResource(Template home, CourseRepository courseRepository) {
        this.home = home;
        this.courseRepository = courseRepository;
    }

    @GET
    public Response showHome(@QueryParam("name") String name) throws SQLException {
        List<Course> courses;
        if(name == null || name.isEmpty()){
            courses = courseRepository.findAll().list();
        }
        else{
            courses = courseRepository.findByName(name);
        }

        return Response.ok(home.data("courses", courses)).build();
    }

    @POST
    @Path("remove")
    public Response remove(@FormParam("code")String code){

        if (code == null || code.isEmpty()){
            return Response.ok(home.data("message", "Errore nel passaggio del codice per la rimozione")).build();
        }

        if(!courseRepository.deleteById(Integer.parseInt(code))){
            return Response.ok(home.data("message", "Errore nella rimozione")).build();
        }

        return Response.ok(home.data("message", null)).build();
    }
}
