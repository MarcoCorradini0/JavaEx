package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import data.model.Course;
import data.repository.CourseRepository;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class BulkInsertService {
    CourseRepository courseRepository;

    public BulkInsertService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public void insertManyCourses(List<Course> courses) throws SQLException {
        for (Course course : courses) {
            courseRepository.persist(course);
        }
    }
}
