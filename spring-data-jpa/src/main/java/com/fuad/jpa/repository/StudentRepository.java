package com.fuad.jpa.repository;

import com.fuad.jpa.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameContaining(String name);

    List<Student> findByLastNameNotNull();

    List<Student> findByGuardianName(String guardian);

    Student findByFirstNameAndLastName(String first, String last);

    //JPQL
    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailAddress(String emailId);

    //JPQL
    @Query("select s.firstName from Student s where s.emailId = ?1")
    String getStudentFirstByEmailAddress(String emailId);

    //native query
    @Query(
        value = "select * from tbl_student where email_address = ?1",
        nativeQuery = true
    )
    String getStudentFirstByEmailAddressNative(String emailId);

    //native query
    @Query(
            value = "select * from tbl_student where email_address = :emailId",
            nativeQuery = true
    )
    String getStudentFirstByEmailAddressNativeNamedParam(@Param("emailId") String emailId);

    @Modifying
    @Transactional
    @Query(
            value = "update tbl_student set first_name = :firstName  where email_address = :emailId",
            nativeQuery = true
    )
    int updateStudentByEmailId(@Param("firstName") String firstName, @Param("emailId") String emailId);
}
