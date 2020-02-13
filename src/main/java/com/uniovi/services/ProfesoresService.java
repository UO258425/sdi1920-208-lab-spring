package com.uniovi.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Profesor;
import com.uniovi.repositories.ProfesoresRepository;

@Service
public class ProfesoresService {
	
	@Autowired
	private ProfesoresRepository profesoresRepository;

	public List<Profesor> getProfesores() {
		List<Profesor> profesors = new ArrayList<Profesor>();
		profesoresRepository.findAll().forEach(profesors::add);
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

	
}
