package com.college.management.service;

import com.college.management.model.Course;
import com.college.management.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    public Course createCourse(Course course) {
        // Check if course name already exists
        if (courseRepository.existsByName(course.getName())) {
            throw new RuntimeException("Course with this name already exists");
        }

        // Check if course code already exists
        if (course.getCourseCode() != null && courseRepository.existsByCourseCode(course.getCourseCode())) {
            throw new RuntimeException("Course with this code already exists");
        }

        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course courseDetails) {
        Course course = getCourseById(id);

        // Check if new course name already exists for another course
        if (!course.getName().equals(courseDetails.getName()) &&
                courseRepository.existsByName(courseDetails.getName())) {
            throw new RuntimeException("Course with this name already exists");
        }

        course.setName(courseDetails.getName());
        course.setDescription(courseDetails.getDescription());
        course.setDuration(courseDetails.getDuration());
        course.setCredits(courseDetails.getCredits());
        course.setCourseCode(courseDetails.getCourseCode());
        course.setDepartment(courseDetails.getDepartment());

        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        Course course = getCourseById(id);
        courseRepository.delete(course);
    }

    public List<Course> searchCoursesByName(String name) {
        return courseRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Course> getCoursesByDepartment(String department) {
        return courseRepository.findByDepartment(department);
    }
}