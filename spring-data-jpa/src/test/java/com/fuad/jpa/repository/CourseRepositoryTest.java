package com.fuad.jpa.repository;

import com.fuad.jpa.entity.Course;
import com.fuad.jpa.entity.Student;
import com.fuad.jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository repository;

    @Test
    public void printAllCourses(){
        List<Course> courses = repository.findAll();
        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Fuad")
                .lastName("Hasan")
                .build();
        Course course = Course.builder()
                .title("DSA")
                .credit(4)
                .teacher(teacher)
                .build();
        repository.save(course);
    }

    @Test
    public void findAllPagination() {

        Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);
        Pageable secondPageWithTwoRecords = PageRequest.of(1, 2);
        
        List<Course> courses = repository.findAll(secondPageWithTwoRecords).getContent();

        long totalElements = repository.findAll(secondPageWithTwoRecords).getTotalElements();
        long totalPages = repository.findAll(secondPageWithTwoRecords).getTotalPages();


        System.out.println("totalPages = " + totalPages);
        System.out.println("totalElements = " + totalElements);
        System.out.println("courses = " + courses);
    }

    @Test
    public void findAllBySorting(){
        Pageable sortByTitle =
                PageRequest.of(0,2, Sort.by("title"));
        Pageable sortByCreditDesc =
                PageRequest.of(0,2, Sort.by("credit").descending());
        Pageable sortByTitleAndCreditDesc =
                PageRequest.of(0, 2, Sort.by("title").descending().and(Sort.by("credit")));
        
        List<Course> courses = repository.findAll(sortByTitle).getContent();
        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Abdul")
                .lastName("Hai")
                .build();
        Student student = Student.builder()
                .firstName("Ali")
                .lastName("Hasan")
                .emailId("ali@inflack.com")
                .build();
        Course course = Course.builder()
                .title("AI")
                .credit(2)
                .teacher(teacher)
                .build();
        course.addStudents(student);

        repository.save(course);
    }
}