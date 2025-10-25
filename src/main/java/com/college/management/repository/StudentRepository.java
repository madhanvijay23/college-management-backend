package com.college.management.repository;

import com.college.management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);

    Optional<Student> findByRollNumber(String rollNumber);

    List<Student> findByCourseContainingIgnoreCase(String course);

    List<Student> findByNameContainingIgnoreCase(String name);

    Boolean existsByEmail(String email);

    Boolean existsByRollNumber(String rollNumber);
}