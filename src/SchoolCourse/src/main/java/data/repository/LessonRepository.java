package data.repository;

import data.model.Lesson;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class LessonRepository implements PanacheRepositoryBase<Lesson, Integer> {

    public List<Lesson> findByCourse(int courseCode){

        return find("SELECT l FROM Lesson l WHERE l.course.code = :courseCode",
                Parameters.with("courseCode", courseCode)).list();
    }

    @Transactional
    public Lesson update(int id, Lesson lesson){

        Lesson oldLesson = findById(id);
        oldLesson.setDate(lesson.getDate());
        oldLesson.setStartHour(lesson.getStartHour());
        oldLesson.setEndHour(lesson.getEndHour());
        oldLesson.setConfirmed(lesson.isConfirmed());
        oldLesson.setRoomCode(lesson.getRoomCode());
        //oldLesson.setCourseCode(lesson.getCourseCode());
        oldLesson.setTeacherCode(lesson.getTeacherCode());

        return oldLesson;
    }

    @Transactional
    public boolean update2(int id, Lesson lesson){

        int modify = update("UPDATE Lesson l SET l.date = :date, l.startHour = :startHour, l.endHour = :endHour," +
                "l.confirmed = :confirmed, l.roomCode = :roomCode, " +
                "l.teacherCode = :teacherCode WHERE l.id = :id",
                Parameters.with("date", lesson.getDate())
                        .and("startHour", lesson.getStartHour())
                        .and("endHour", lesson.getEndHour())
                        .and("confirmed", lesson.isConfirmed())
                        .and("roomCode", lesson.getRoomCode())
                        //.and("courseCode", lesson.getCourseCode())
                        .and("teacherCode", lesson.getTeacherCode())
                        .and("id", id));

        lesson.setId(id);

        return modify > 0;
    }
}
