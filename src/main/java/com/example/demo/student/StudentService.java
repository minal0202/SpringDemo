package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.email);
        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new IllegalStateException("Student with id:" + studentId + " does not exist");
        } else {
            studentRepository.deleteById(studentId);
        }
    }

    @Transactional
    public void updateStudent(Long studentId, String studentName, String studentEmail) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Student with student id " + studentId + " does not exist"));

        if(studentName != null && !studentName.isEmpty() && !student.name.equals(studentName)) {
            student.setName(studentName);
        }

        if(studentEmail != null && !studentEmail.isEmpty() && !student.email.equals(studentEmail)) {
            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(studentEmail);
            if (studentByEmail.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(studentEmail);
        }
    }
}
