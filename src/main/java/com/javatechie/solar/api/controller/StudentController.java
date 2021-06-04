package com.javatechie.solar.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.javatechie.solar.api.model.Student;
import com.javatechie.solar.api.repository.StudentRepository;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository repository;

    @PostConstruct
    public void addStudent() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Basant", new String[]{"Bangalore", "BTM"}));
        students.add(new Student(2, "Santosh", new String[]{"Hyderbad", "XYZ"}));
        students.add(new Student(3, "Sagar", new String[]{"Pune", "PQR"}));
        repository.saveAll(students);
    }

    @GetMapping("/getALL")
    public Iterable<Student> getStudent() {
        return repository.findAll();
    }

    @GetMapping("/getStudent/{name}")
    public Student getStudentByName(@PathVariable String name) {
        return repository.findByName(name);
    }

    @PostMapping("/create")
    public ResponseEntity<Student> create(@RequestBody Student student) {
        return new ResponseEntity<>(repository.save(student),HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void>delete(@RequestParam Long id){
        repository.delete(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("update")
    public  ResponseEntity<Student>update(@RequestBody Student student, @PathVariable Long id){
        Optional<Student> employeeOptional=repository.findById(id);
       return  new ResponseEntity<>(repository.save(student),HttpStatus.OK);
    }

}