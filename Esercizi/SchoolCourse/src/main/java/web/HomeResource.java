package web;

import java.util.Collections;
import java.util.List;

import data.model.Course;
import io.quarkus.qute.Template;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import service.CourseService;

@Path("/")
public class HomeResource {

    private final Template home;
    private final CourseService courseService;

    public HomeResource(Template home, CourseService courseService) {
        this.home = home;
        this.courseService = courseService;
    }

    @GET
    public Response showHome(@QueryParam("name") String name) {
        List<Course> courses;
        if (name == null || name.isEmpty()) {
            courses = courseService.findAll();
        } else {
            courses = courseService.findByName(name);
        }

        return Response.ok(
                home.data("courses", courses == null ? Collections.emptyList() : courses)
                        .data("message", null))
                .build();
    }

    @GET
    @Path("/api/courses")
    @jakarta.ws.rs.Produces("application/json")
    public List<Course> getCourses(@QueryParam("name") String name) {
        if (name == null || name.isEmpty()) {
            return courseService.findAll();
        } else {
            return courseService.findByName(name);
        }
    }

    @POST
    @Path("remove")
    public Response remove(@FormParam("code") String code) {
        List<Course> courses = courseService.findAll();

        if (code == null || code.isEmpty()) {
            return Response.ok(
                    home.data("courses", courses)
                            .data("message", "Errore nel passaggio del codice per la rimozione"))
                    .build();
        }

        try {
            int id = Integer.parseInt(code);
            courseService.deleteById(id);

            courses = courseService.findAll(); // Ricarica dopo la rimozione
            return Response.ok(
                    home.data("courses", courses) //togliere per fare con fetch?
                            .data("message", "Corso rimosso con successo"))
                    .build();
        } catch (NumberFormatException e) {
            return Response.ok(
                    home.data("courses", courses)
                            .data("message", "Codice non valido"))
                    .build();
        } catch (Exception e) {
            return Response.ok(
                    home.data("courses", courses)
                            .data("message", "Errore nella rimozione"))
                    .build();
        }
    }
}
