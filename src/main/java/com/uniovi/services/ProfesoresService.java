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
	
	private List<Profesor> profesoresList = new LinkedList<Profesor>();
	
	@PostConstruct
	public void init() {
		profesoresList.add(new Profesor(1L, "asdf", "nombre1", "Apellidos1", "categoria1"));
		profesoresList.add(new Profesor(2L, "dni2", "nombre2", "apellidos2", "categoria2"));
	}




	public List<Profesor> getProfesores() {
		List<Profesor> profesors = new ArrayList<Profesor>();
		profesoresRepository.findAll().forEach(profesors::add);
		//return profesors;
		return profesoresList;
	}

	public Profesor getProfesor(Long id) {
		//return profesoresRepository.findById(id).get();
		return profesoresList.stream().filter(p -> p.getId()==id).findFirst().orElse(null);
	}

	public void addProfesor(Profesor profesor) {
		//profesoresRepository.save(profesor);
		if (profesor.getId() == null) {
			profesor.setId(profesoresList.get(profesoresList.size() - 1).getId() + 1);
		}
		profesoresList.add(profesor);
	}

	public void deleteProfesor(Long id) {
		//profesoresRepository.deleteById(id);
		profesoresList.removeIf(p -> p.getId().equals(id));
	}

	
}
