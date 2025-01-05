package com.alura.ForoHubAPI.domain.model;

import com.alura.ForoHubAPI.dto.course.CourseDTO;
import com.alura.ForoHubAPI.dto.course.RegisterCourseDTO;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Course")
@Table(name = "courses")
@ToString
@EqualsAndHashCode(of = "courseId")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;

    public Course() {
    }

    public Course(Long courseId, String name, Category category) {
        this.courseId = courseId;
        this.name = name;
        this.category = category;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Course(RegisterCourseDTO data) {
        this.name = data.name();
        this.category = data.category();
    }

    public void updateCourse(CourseDTO data) {
        if(data.name() != null){
            this.name = data.name();
        }
        if(data.category() != null){
            this.category = data.category();
        }
    }
}

