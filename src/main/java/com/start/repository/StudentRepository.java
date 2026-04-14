package com.start.repository;

import org.springframework.data.repository.CrudRepository;

import com.start.entity.StudentEntity;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer>{

}
