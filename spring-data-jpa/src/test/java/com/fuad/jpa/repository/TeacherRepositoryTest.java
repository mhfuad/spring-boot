package com.fuad.jpa.repository;

import com.fuad.jpa.entity.Course;
import com.fuad.jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository repository;

    //not working
    @Test
    public void saveTeacher(){
        Course course = Course.builder()
                .title("Java")
                .credit(12)
                .build();
        Course course1 = Course.builder()
                .title("Rust")
                .credit(6)
                .build();
        Teacher teacher = Teacher.builder()
                .firstName("Mahadi")
                .lastName("Hasan")
                //.courses(List.of(course, course1))
                .build();
        repository.save(teacher);
    }
}