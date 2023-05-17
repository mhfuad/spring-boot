package com.fuad.jpa.repository;

import com.fuad.jpa.entity.Guardian;
import com.fuad.jpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .firstName("Fuad")
                .lastName("Hasan")
                .emailId("fuad@inflack.com")
//                .guardianName("Abdul Motalib")
//                .guardianEmail("motalib@biswas.com")
//                .guardianMobile("01727507024")
                .build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .name("Abdul Motalib")
                .email("motalib@biswas.com")
                .mobile("01727507024")
                .build();
        Student student = Student.builder()
                .firstName("Mahadi")
                .lastName("Hasan")
                .emailId("mahadi@inflack.com")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }

    @Test
    public void printAllStudent(){
        List<Student> studentList = studentRepository.findAll();
        System.out.println("studentList = " + studentList);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> students = studentRepository.findByFirstName("mahadi");
        System.out.println("students = " + students);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students = studentRepository.findByFirstNameContaining("adi");
        System.out.println("students = " + students);
    }

    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> students = studentRepository.findByGuardianName("Abdul Motalib");
        System.out.println("students = " + students);
    }

    @Test
    public void printStudentBasedOnAddress(){
        Student student = studentRepository.getStudentByEmailAddress("fuad@inflack.com");
        System.out.println("student = " + student);
    }

    @Test
    public void printStudentFirstBasedOnAddress(){
        String student = studentRepository.getStudentFirstByEmailAddress("fuad@inflack.com");
        System.out.println("student = " + student);
    }

    @Test
    public void getStudentFirstByEmailAddressNative(){
        String student = studentRepository.getStudentFirstByEmailAddressNative("mahadi@inflack.com");
        System.out.println("student = " + student);
    }

    @Test
    public void updateStudentByEmailIdTest(){
        studentRepository.updateStudentByEmailId("mahadi2", "mahadi@inflack.com");
    }

}