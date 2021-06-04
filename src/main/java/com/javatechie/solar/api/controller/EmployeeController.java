package com.javatechie.solar.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.javatechie.solar.api.model.Employee;
import com.javatechie.solar.api.repository.EmployeeRepository;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @PostConstruct
    public void addEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Basant", new String[]{"Bangalore", "BTM"}));
        employees.add(new Employee(2, "Santosh", new String[]{"Hyderbad", "XYZ"}));
        employees.add(new Employee(3, "Sagar", new String[]{"Pune", "PQR"}));
        repository.saveAll(employees);
    }

    @GetMapping("/getALL")
    public Iterable<Employee> getEmployees() {
        return repository.findAll();
    }

    @GetMapping("/getEmployee/{name}")
    public Employee getEmployeeByName(@PathVariable String name) {
        return repository.findByName(name);
    }

    @PostMapping("/create")
    public ResponseEntity<Employee> create(@RequestBody Employee employee  ) {
        return new ResponseEntity<>(repository.save(employee),HttpStatus.CREATED);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void>delete(@RequestParam Long id){
        repository.delete(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("update")
    public  ResponseEntity<Employee>update(@RequestBody Employee employee, @PathVariable Long id){
        Optional<Employee> employeeOptional=repository.findById(id);
       return  new ResponseEntity<>(repository.save(employee),HttpStatus.OK);
    }

}