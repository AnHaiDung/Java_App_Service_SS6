package com.demo.ss6.service;

import com.demo.ss6.model.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    Page<Student> findAll(Integer page, Integer size);
    Student getStudentbyId(Long id);
    Student addStudent(Student student);
    Student updateStudent(Long id, Student studentDetails);
    Student updatePartialStudent(Long id, Student studentDetails);
    boolean deleteStudent(Long id);
}
