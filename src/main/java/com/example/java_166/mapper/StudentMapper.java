package com.example.java_166.mapper;

import com.example.java_166.dao.StudentEntity;
import com.example.java_166.model.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentEntity toEntity(StudentDto studentDto);

    StudentDto toDto(StudentEntity studentEntity);

    StudentEntity forUpdate(StudentDto studentDto, @MappingTarget StudentEntity studentEntity);
}
