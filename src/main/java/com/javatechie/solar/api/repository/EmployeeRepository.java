package com.javatechie.solar.api.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.javatechie.solar.api.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface EmployeeRepository extends SolrCrudRepository<Employee, Integer>{

	Employee findByName(String name);

	void delete(Long id);

	Optional<Employee> findById(Long id);
}
