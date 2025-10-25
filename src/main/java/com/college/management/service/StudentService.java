package com.college.management.service;

import com.college.management.model.Student;
import com.college.management.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    public Student createStudent(Student student) {
        // Check if email already exists
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new RuntimeException("Student with this email already exists");
        }

        // Check if roll number already exists
        if (student.getRollNumber() != null && studentRepository.existsByRollNumber(student.getRollNumber())) {
            throw new RuntimeException("Student with this roll number already exists");
        }

        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student student = getStudentById(id);

        // Check if new email already exists for another student
        if (!student.getEmail().equals(studentDetails.getEmail()) &&
                studentRepository.existsByEmail(studentDetails.getEmail())) {
            throw new RuntimeException("Student with this email already exists");
        }

        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        student.setPhone(studentDetails.getPhone());
        student.setCourse(studentDetails.getCourse());
        student.setEnrollmentDate(studentDetails.getEnrollmentDate());
        student.setAddress(studentDetails.getAddress());
        student.setDateOfBirth(studentDetails.getDateOfBirth());
        student.setGender(studentDetails.getGender());
        student.setRollNumber(studentDetails.getRollNumber());

        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }

    public List<Student> searchStudentsByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Student> getStudentsByCourse(String course) {
        return studentRepository.findByCourseContainingIgnoreCase(course);
    }
}
