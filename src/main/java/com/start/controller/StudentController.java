package com.start.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.start.dto.StudentDTO;
import com.start.exception.StudentException;
import com.start.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/getall")
    public ResponseEntity<List<StudentDTO>> getAllStudents() throws StudentException{
        List<StudentDTO>studentDTOs =  studentService.getAllStudents();
        return new ResponseEntity<>(studentDTOs, HttpStatus.OK);
    }

    @GetMapping("/get/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Integer studentId) throws StudentException{
        StudentDTO studentDTO =  studentService.getStudentById(studentId);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) throws StudentException{
        StudentDTO studentDTOReturned =  studentService.createStudent(studentDTO);
        return new ResponseEntity<>(studentDTOReturned, HttpStatus.CREATED);
    }

    @PutMapping("/put/{studentId}/{age}")
    public ResponseEntity<StudentDTO> updateStudentAge(@PathVariable Integer studentId, @PathVariable Integer age) throws StudentException{
        StudentDTO studentDTO =  studentService.updateStudentAge(studentId, age);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer studentId) throws StudentException{
        Integer studentIdReturned = studentService.deleteStudent(studentId);
        String response = "Student deleted successfully: "+studentIdReturned;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
