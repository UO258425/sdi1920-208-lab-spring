package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Profesor;

public interface ProfesoresRepository extends CrudRepository<Profesor, Long> {
	Profesor findByDni(String dni);

	Page<Profesor> findAll(Pageable pageable);

	
}
