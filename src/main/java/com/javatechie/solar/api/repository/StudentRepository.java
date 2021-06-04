package com.javatechie.solar.api.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.javatechie.solar.api.model.Student;

import java.util.Optional;


public interface StudentRepository extends SolrCrudRepository<Student, Integer>{

	Student findByName(String name);

	void delete(Long id);

	Optional<Student> findById(Long id);
}
