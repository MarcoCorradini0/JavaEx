package data.model;

import com.fasterxml.jackson.annotation.JacksonInject;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Date", nullable = false)
    private LocalDate date;

    @Column(name = "StartHour", nullable = false)
    private LocalTime startHour;

    @Column(name = "EndHour", nullable = false)
    private LocalTime endHour;

    @Column(name = "Confirmed")
    private boolean confirmed;

    @Column(name = "RoomCode", length = 20)
    private String roomCode;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CourseCode")
    private Course course;

    @Column(name = "TeacherCode")
    private int teacherCode;

    public Lesson() {}

    public Lesson(LocalDate date, LocalTime startHour, LocalTime endHour, boolean confirmed, String roomCode, Course course, int teacherCode) {
        this.date = date;
        this.startHour = startHour;
        this.endHour = endHour;
        this.confirmed = confirmed;
        this.roomCode = roomCode;
        this.course = course;
        this.teacherCode = teacherCode;
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public void setStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    public void setEndHour(LocalTime endHour) {
        this.endHour = endHour;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(int teacherCode) {
        this.teacherCode = teacherCode;
    }

}
