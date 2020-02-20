package com.uniovi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Profesor;
import com.uniovi.entities.User;

public interface ProfesoresRepository extends CrudRepository<Profesor, Long> {
	Profesor findByDni(String dni);

}
