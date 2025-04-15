package com.example.java_166.controller;

import com.example.java_166.model.StudentDto;
import com.example.java_166.service.StudentService;
import com.example.java_166.specification.GpaSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<StudentDto> getAll(GpaSpecification gpaSpecification, Pageable pageable){
        return studentService.getAll(gpaSpecification, pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDto getById(@PathVariable Long id){
        return studentService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody StudentDto studentDto){
        studentService.create(studentDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody StudentDto studentDto, @PathVariable Long id){
        studentService.update(studentDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        studentService.delete(id);
    }
}
