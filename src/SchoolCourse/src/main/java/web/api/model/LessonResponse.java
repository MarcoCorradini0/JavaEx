package web.api.model;

import data.model.Course;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class LessonResponse {

    private final Integer id;
    private final LocalDate date;
    private final LocalTime startHour;
    private final LocalTime endHour;
    private final boolean confirmed;
    private final String roomCode;
    private final int courseCode;
    private final Integer teacherCode;

    public LessonResponse(Integer id, LocalDate date, LocalTime startHour, LocalTime endHour, boolean confirmed, String roomCode, int courseCode, Integer teacherCode) {
        this.id = id;
        this.date = date;
        this.startHour = startHour;
        this.endHour = endHour;
        this.confirmed = confirmed;
        this.roomCode = roomCode;
        this.courseCode = courseCode;
        this.teacherCode = teacherCode;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public Integer getTeacherCode() {
        return teacherCode;
    }
}
