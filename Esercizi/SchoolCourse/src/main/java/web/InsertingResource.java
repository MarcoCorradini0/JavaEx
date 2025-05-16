package web;

import java.net.URI;
import java.time.LocalDate;

import data.model.Course;
import data.repository.CourseRepository;
import io.quarkus.qute.Template;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/addCourse")
public class InsertingResource {
    private final Template inserting;
    private final CourseRepository cr;

    public InsertingResource(Template inserting, CourseRepository cr) {
        this.inserting = inserting;
        this.cr = cr;
    }

    @GET
    public Response showInserting() {
        return Response.ok(inserting.data("message", null)).build();
    }

    @POST
    @Transactional
    public Response insert(@FormParam("name") String name, @FormParam("description") String description, @FormParam("totalHour") String totalHour, @FormParam("startDate")LocalDate startDate, @FormParam("endDate") LocalDate endDate) {
        if (name != null && totalHour != null && startDate != null && endDate != null &&
            !name.isEmpty() && !totalHour.isEmpty() && !startDate.isAfter(endDate)) {
            Course course = new Course(0, name, description, Integer.parseInt(totalHour), startDate, endDate);
            cr.persist(course);
            return Response.seeOther(URI.create("/")).build();
        }
        return Response.ok(inserting.data("message", "ERRORE, DATI NON VALIDI")).build();
    }
}
