package com.start.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.start.dto.StudentDTO;
import com.start.entity.StudentEntity;
import com.start.exception.StudentException;
import com.start.repository.StudentRepository;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentDTO> getAllStudents() throws StudentException {
        List<StudentEntity> studentEntities = (List<StudentEntity>) studentRepository.findAll();
        if(studentEntities.isEmpty()){
            throw new StudentException("No Students Found");
        }
        List<StudentDTO> studentDTOs = new ArrayList<>();
        for(StudentEntity student : studentEntities){
            StudentDTO studentDTO = entityToDTO(student);
            studentDTOs.add(studentDTO);
        }
        return studentDTOs;
    }

    @Override
    public StudentDTO getStudentById(Integer studentId) throws StudentException {
        Optional<StudentEntity> optional = studentRepository.findById(studentId);
        if(optional.isEmpty()){
            throw new StudentException("Student Not Found");
        }
        StudentDTO studentDTO = entityToDTO(optional.get());
        return studentDTO;
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) throws StudentException {
        StudentEntity entity = DTOToEntity(studentDTO);
        StudentEntity student = studentRepository.save(entity);
        StudentDTO studentDTOReturned = entityToDTO(student);
        return studentDTOReturned;
    }

    @Override
    public StudentDTO updateStudentAge(Integer studentId, Integer age) throws StudentException {
        Optional<StudentEntity> optional = studentRepository.findById(studentId);
        if(optional.isEmpty()){
            throw new StudentException("Student Not Found");
        }
        StudentEntity studentEntity = optional.get();
        studentEntity.setAge(age);
        studentRepository.save(studentEntity);
        StudentDTO studentDTO = entityToDTO(studentEntity);
        return studentDTO;
    }

    @Override
    public Integer deleteStudent(Integer studentId) throws StudentException {
        Optional<StudentEntity> optional = studentRepository.findById(studentId);
        if(optional.isEmpty()){
            throw new StudentException("Student Not Found");
        }
        studentRepository.deleteById(studentId);
         Optional<StudentEntity> optionalCheck = studentRepository.findById(studentId);
        if(!optionalCheck.isEmpty()){
            throw new StudentException("Student Not Found");
        }
        return studentId;
    }

    private StudentDTO entityToDTO(StudentEntity student){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(student.getStudentId());
        studentDTO.setAge(student.getAge());
        studentDTO.setStudentName(student.getStudentName());
        studentDTO.setCity(student.getCity());
        studentDTO.setWeight(student.getWeight());
        return studentDTO;
    }

    private StudentEntity DTOToEntity(StudentDTO studentDTO){
        StudentEntity student = new StudentEntity();
        student.setStudentName(studentDTO.getStudentName());
        student.setAge(studentDTO.getAge());
        student.setCity(studentDTO.getCity());
        student.setWeight(studentDTO.getWeight());
        return student;
    }

}
