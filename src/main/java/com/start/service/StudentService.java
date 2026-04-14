package com.start.service;

import java.util.List;

import com.start.dto.StudentDTO;
import com.start.exception.StudentException;

public interface StudentService {

    public List<StudentDTO> getAllStudents() throws StudentException;
    public StudentDTO getStudentById(Integer studentId) throws StudentException;
    public StudentDTO createStudent(StudentDTO studentDTO) throws StudentException;
    public StudentDTO updateStudentAge(Integer studentId, Integer age) throws StudentException;
    public Integer deleteStudent(Integer studentId) throws StudentException;
}
