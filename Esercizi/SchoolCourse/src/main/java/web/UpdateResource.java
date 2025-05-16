package web;

import java.net.URI;
import java.sql.SQLException;
import java.time.LocalDate;

import data.model.Course;
import data.repository.CourseRepository;
import io.quarkus.qute.Template;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/update")
public class UpdateResource {
    private final Template update;
    private final CourseRepository cr;
    private final CourseRepository courseRepository;

    public UpdateResource(Template update, CourseRepository cr, CourseRepository courseRepository) {
        this.update = update;
        this.cr = cr;
        this.courseRepository = courseRepository;
    }

    @GET
    public Response showInserting(@QueryParam("code")String code) throws SQLException {
        Course cr = courseRepository.findById(Integer.parseInt(code));
        return Response.ok(update.data("message", null, "course", cr)).build();
    }

    @POST
    public Response insert(@FormParam("name") String name, @FormParam("description") String description, @FormParam("totalHour") String totalHour, @FormParam("startDate")LocalDate startDate, @FormParam("endDate") LocalDate endDate, @FormParam("codice") String codice) throws SQLException {
        if (name != null && totalHour != null && startDate != null && endDate != null &&
                !name.isEmpty() && !totalHour.isEmpty() && !startDate.isAfter(endDate)) {
            Course course = new Course(Integer.parseInt(codice), name, description, Integer.parseInt(totalHour), startDate, endDate);
            cr.updateCourse(course, Integer.parseInt(codice));
            return Response.seeOther(URI.create("/")).build();
        }
        Course cr1 = courseRepository.findById(Integer.parseInt(codice));
        return Response.ok(update.data("message", "ERRORE, DATI NON VALIDI", "course", cr1)).build();
    }
}
