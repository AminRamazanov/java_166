package com.example.java_166.service.impl;

import com.example.java_166.dao.StudentEntity;
import com.example.java_166.dao.repository.StudentRepository;
import com.example.java_166.mapper.StudentMapper;
import com.example.java_166.model.StudentDto;
import com.example.java_166.service.StudentService;
import com.example.java_166.specification.GpaSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentDto getById(Long id) {
        StudentEntity studentEntity = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
        StudentDto studentDto = studentMapper.toDto(studentEntity);
        return studentDto;
    }

    @Override
    public Page<StudentDto> getAll(GpaSpecification gpaSpecification, Pageable pageable) {
        var specification = Specification.where(gpaSpecification);
        Page<StudentEntity> studentEntityPage = studentRepository.findAll(specification, pageable);
        List<StudentDto> studentDtoList = studentEntityPage.stream().map(studentMapper::toDto).toList();
        PageImpl<StudentDto> studentDtoPage =
                new PageImpl<>(studentDtoList, pageable, studentEntityPage.getTotalElements());
        return studentDtoPage;
    }

    @Override
    public void create(StudentDto studentDto) {
        StudentEntity studentEntity = studentMapper.toEntity(studentDto);
        studentRepository.save(studentEntity);
    }

    @Override
    public void update(StudentDto studentDto, Long id) {
        StudentEntity studentEntity = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
        studentEntity = studentMapper.forUpdate(studentDto, studentEntity);
        studentRepository.save(studentEntity);
    }

    @Override
    public void delete(Long id) {
        StudentEntity studentEntity = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
    }
}
