package com.demo.ss6.service.impl;

import com.demo.ss6.model.entity.Student;
import com.demo.ss6.repository.StudentRepository;
import com.demo.ss6.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public Page<Student> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1,size);
        return studentRepository.findAll(pageable);
    }


    @Override
    public Student getStudentbyId(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student addStudent(Student student) {
        student.setId(null);
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long id, Student studentDetails) {
        Student existingStudent = studentRepository.findById(id).orElse(null);

        if (existingStudent == null) {
            return null;
        }

        existingStudent.setFullName(studentDetails.getFullName());
        existingStudent.setEmail(studentDetails.getEmail());
        existingStudent.setGpa(studentDetails.getGpa());
        return studentRepository.save(existingStudent);
    }

    @Override
    public Student updatePartialStudent(Long id, Student studentDetails) {
        Student existingStudent = studentRepository.findById(id).orElse(null);

        if (existingStudent == null) {
            return null;
        }
        if (studentDetails.getFullName() != null && !studentDetails.getFullName().trim().isEmpty()) {
            existingStudent.setFullName(studentDetails.getFullName());
        }
        if (studentDetails.getEmail() != null && !studentDetails.getEmail().trim().isEmpty()) {
            existingStudent.setEmail(studentDetails.getEmail());
        }
        if (studentDetails.getGpa() > 0) {
            existingStudent.setGpa(studentDetails.getGpa());
        }

        return studentRepository.save(existingStudent);
    }

    @Override
    public boolean deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
