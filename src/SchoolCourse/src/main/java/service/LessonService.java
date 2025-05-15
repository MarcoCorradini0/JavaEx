package service;

import data.model.Course;
import data.model.Lesson;
import data.repository.CourseRepository;
import data.repository.LessonRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import web.api.model.LessonCreateRequest;
import web.api.model.LessonResponse;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class LessonService {

    private LessonRepository lessonRepository;
    private CourseRepository courseRepository;

    public LessonService(LessonRepository lessonRepository, CourseRepository courseRepository) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
    }

    public List<LessonResponse> findAll(){
        List<Lesson> lessons = lessonRepository.findAll().list();

        List<LessonResponse> lessonResponses = new ArrayList<>();

        for (Lesson lesson : lessons) {
            lessonResponses.add(getLessonResponse(lesson));
        }

        return lessonResponses;
    }

    public List<LessonResponse> findByCourse(int courseCode){
        List<Lesson> lessons = lessonRepository.findByCourse(courseCode);

        List<LessonResponse> lessonResponses = new ArrayList<>();

        for (Lesson lesson : lessons) {
            lessonResponses.add(getLessonResponse(lesson));
        }

        return lessonResponses;
    }

    @Transactional
    public LessonResponse persist(LessonCreateRequest lesson){
        Course course= courseRepository.findById(lesson.getCourseCode());

        Lesson entity = getLesson(lesson, course);

        lessonRepository.persist(entity);

        return getLessonResponse(entity);
    }

    @Transactional
    public LessonResponse update(LessonCreateRequest lessonModify, int id){
        Course course= courseRepository.findById(lessonModify.getCourseCode());

        Lesson entity = getLesson(lessonModify, course);

        boolean modified = lessonRepository.update2(id, entity);

        if (modified){
            return getLessonResponse(entity);
        }

        throw new EntityNotFoundException();
    }

    @Transactional
    public boolean deleteById(int id){
        return lessonRepository.deleteById(id);
    }

    private static LessonResponse getLessonResponse(Lesson lesson) {
        return new LessonResponse(
                lesson.getId(),
                lesson.getDate(),
                lesson.getStartHour(),
                lesson.getEndHour(),
                lesson.isConfirmed(),
                lesson.getRoomCode(),
                lesson.getCourse().getCode(),
                lesson.getTeacherCode());
    }

    private static Lesson getLesson(LessonCreateRequest lesson, Course course) {
        Lesson entity = new Lesson(
                lesson.getDate(),
                lesson.getStartHour(),
                lesson.getEndHour(),
                lesson.isConfirmed(),
                lesson.getRoomCode(),
                course,
                lesson.getTeacherCode());
        return entity;
    }

}
