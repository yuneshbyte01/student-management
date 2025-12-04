package com.studentmanagementsystem.service;

import com.studentmanagementsystem.model.Student;
import com.studentmanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get a student by ID
    public Student getStudentById(int id) {
        Optional<Student> foundStudent = studentRepository.findById(id);

        if (foundStudent.isPresent()) {
            return foundStudent.get();
        } else {
            System.out.println("Student with ID " + id + " not found!");
            return null;
        }
    }

    // Add a new student
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // Update an existing student
    public Student updateStudent(int id, Student newStudentData) {
        Optional<Student> existingStudentOpt = studentRepository.findById(id);

        if (existingStudentOpt.isEmpty()) {
            System.out.println("Cannot update. Student with ID " + id + " not found!");
            return null;
        }

        Student existingStudent = existingStudentOpt.get();

        existingStudent.setName(newStudentData.getName());
        existingStudent.setEmail(newStudentData.getEmail());
        existingStudent.setCourse(newStudentData.getCourse());

        return studentRepository.save(existingStudent);
    }

    // Delete a student
    public void deleteStudent(int id) {
        Optional<Student> studentOpt = studentRepository.findById(id);

        if (studentOpt.isEmpty()) {
            System.out.println("Cannot delete. Student with ID " + id + " not found!");
        }

        studentRepository.deleteById(id);
        System.out.println("Student with ID " + id + "deleted successfully!");
    }
}
