package com.example.java_166.service;

import com.example.java_166.model.StudentDto;
import com.example.java_166.specification.GpaSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {

    StudentDto getById(Long id);

    Page<StudentDto> getAll(GpaSpecification gpaSpecification, Pageable pageable);

    void create(StudentDto studentDto);

    void update(StudentDto studentDto, Long id);

    void delete(Long id);
}
