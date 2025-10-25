package com.college.management.repository;

import com.college.management.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByName(String name);

    Optional<Course> findByCourseCode(String courseCode);

    List<Course> findByNameContainingIgnoreCase(String name);

    List<Course> findByDepartment(String department);

    Boolean existsByName(String name);

    Boolean existsByCourseCode(String courseCode);
}
