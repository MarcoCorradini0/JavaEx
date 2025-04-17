package service;

import java.util.List;

import data.model.Course;
import data.repository.CourseRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public void deleteById(int id) {
        courseRepository.deleteById(id);
    }

    public List<Course> findAll() {
        // Recupera tutti i corsi
        return courseRepository.findAll().list();
    }

    public List<Course> findByName(String name) {
        // Recupera corsi filtrati per nome
        return courseRepository.find("SELECT c FROM Course c WHERE c.name = :name", 
                                     Parameters.with("name", name)).list();
    }
}