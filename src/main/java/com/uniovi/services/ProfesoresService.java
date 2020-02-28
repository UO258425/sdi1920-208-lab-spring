package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Mark;
import com.uniovi.entities.Profesor;
import com.uniovi.repositories.ProfesoresRepository;

@Service
public class ProfesoresService {
	
	@Autowired
	private ProfesoresRepository profesoresRepository;

	public Page<Profesor> getProfesores(Pageable pageable) {
		Page<Profesor> profesors = profesoresRepository.findAll(pageable);
		return profesors;
	}

	public Profesor getProfesor(Long id) {
		return profesoresRepository.findById(id).get();
	}

	public void addProfesor(Profesor profesor) {
		profesoresRepository.save(profesor);
		
	}

	public void deleteProfesor(Long id) {
		profesoresRepository.deleteById(id);
	}
	public Profesor getProfesorByDni(String dni) {
		return profesoresRepository.findByDni(dni);
	}
	
}
