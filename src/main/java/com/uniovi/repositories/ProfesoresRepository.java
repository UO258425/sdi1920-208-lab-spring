package com.uniovi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Profesor;

public interface ProfesoresRepository extends CrudRepository<Profesor, Long> {
	Profesor findByDni(String dni);

}
