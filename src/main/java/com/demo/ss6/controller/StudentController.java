package com.demo.ss6.controller;

import com.demo.ss6.model.dto.response.ApiDataResponse;
import com.demo.ss6.model.entity.Student;
import com.demo.ss6.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;



@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<ApiDataResponse<Page<Student>>> findAll(@RequestParam (value = "page",defaultValue = "1")Integer page) {
        int size = 2;
        return new ResponseEntity<>(new ApiDataResponse<>(true, "Lấy danh sách sản phẩm thành công!", studentService.findAll(page,size),
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Student>> getStudentbyId(@PathVariable("id") Long id) {
        Student student = studentService.getStudentbyId(id);
        if (student != null) {
            return new ResponseEntity<>(new ApiDataResponse<>(
                    true,
                    "Tìm thấy" + id + " thành công!",
                    student,
                    HttpStatus.OK
            ), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiDataResponse<>(
                    false,
                    "Không tìm thấy " + id,
                    null,
                    HttpStatus.NOT_FOUND
            ), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<Student>> addStudent( @RequestBody Student student) {
        Student savedStudent = studentService.addStudent(student);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Thêm mới " + student.getFullName() + " thành công!",
                savedStudent,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Student>> updateStudent(@PathVariable("id") Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);

        if (updatedStudent != null) {
            return new ResponseEntity<>(new ApiDataResponse<>(
                    true,
                    "Cập nhật " + id + " thành công",
                    updatedStudent,
                    HttpStatus.OK
            ), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiDataResponse<>(
                    false,
                    "Cập nhật thất bại " + id,
                    null,
                    HttpStatus.NOT_FOUND
            ), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Student>> updatePartialStudent(
            @RequestBody Student student,
            @PathVariable("id") Long id) {

        Student patchedStudent = studentService.updatePartialStudent(id, student);

        if (patchedStudent != null) {
            return new ResponseEntity<>(new ApiDataResponse<>(
                    true,
                    "Cập nhật một phana" + id + " thành công",
                    patchedStudent,
                    HttpStatus.OK
            ), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiDataResponse<>(
                    false,
                    "Cập nhật thất bại  " + id,
                    null,
                    HttpStatus.NOT_FOUND
            ), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Void>> deleteStudent(@PathVariable("id") Long id) {
        boolean isDeleted = studentService.deleteStudent(id);

        if (isDeleted) {
            return new ResponseEntity<>(new ApiDataResponse<>(
                    true,
                    "Xóa" + id + " thành công",
                    null,
                    HttpStatus.NO_CONTENT
            ), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(new ApiDataResponse<>(
                    false,
                    "Xóa thất bại" + id,
                    null,
                    HttpStatus.NOT_FOUND
            ), HttpStatus.NOT_FOUND);
        }
    }
}
