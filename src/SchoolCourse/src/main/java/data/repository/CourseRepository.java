package data.repository;

import data.model.Course;
import io.agroal.api.AgroalDataSource;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CourseRepository implements PanacheRepositoryBase<Course, Integer> {

    public List<Course> findByName(String name) {
        return find("SELECT c FROM Course c WHERE c.name = :name",
                Parameters.with("name", name)).list();
    }

    public boolean updateCourse(Course course, int code){
        int modify = update("UPDATE Course c SET c.name = :name, c.description = :description, c.totalHour = :totalHour," +
                        "c.startDate = :startDate, c.endDate = :endDate WHERE c.code = :code",
                Parameters.with("name", course.getName())
                        .and("description", course.getDescription())
                        .and("totalHour", course.getTotalHour())
                        .and("startDate", course.getStartDate())
                        .and("endDate", course.getEndDate())
                        .and("code", code));

        course.setCode(code);

        return modify > 0;
    }

}