package data.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private int code;

    @Column(name = "Name", length = 40, nullable = false)
    private String name;

    @Lob
    @Column(name = "Description", nullable = false, columnDefinition = "text")
    private String description;

    @Column(name = "TotalHour", nullable = false)
    private int totalHour;

    @Column(name = "StartDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "EndDate", nullable = false)
    private LocalDate endDate;

    @OneToMany
    @JoinColumn(name = "CourseCode")
    private Collection<Lesson> lessons = new ArrayList<>();

    public Course() {}

    public Course(int code, String name, String description, int totalHour, LocalDate startDate, LocalDate endDate) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.totalHour = totalHour;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(int totalHour) {
        this.totalHour = totalHour;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


    public Collection<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Collection<Lesson> lessons) {
        this.lessons = lessons;
    }
}
