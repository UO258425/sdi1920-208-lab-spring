package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.entities.Profesor;
import com.uniovi.services.ProfesoresService;

@Controller
public class ProfesoresController {

	
	@Autowired
	private ProfesoresService profesoresService;
	
	@RequestMapping("/profesor/list")
	public String getList(Model model) {
		model.addAttribute("profesoresList", profesoresService.getProfesores());
		return "profesor/list";
	}
	
	@RequestMapping(value = "/profesor/add", method = RequestMethod.POST)
	public String setProfesor(@ModelAttribute Profesor profesor) {
		profesoresService.addProfesor(profesor);
		return "redirect:/profesor/list";
	}
	
	@RequestMapping("/profesor/add")
	public String getProfesor() {
		return "profesor/add";
	}
	
	@RequestMapping("/profesor/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("profesor", profesoresService.getProfesor(id));
		return "profesor/details";
	}

	
	@RequestMapping("/profesor/delete/{id}")
	public String deleteProfesor(@PathVariable Long id) {
		profesoresService.deleteProfesor(id);
		return "redirect:/profesor/list";
	}
	
	@RequestMapping(value="/profesor/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id){
		model.addAttribute("mark", profesoresService.getProfesor(id));
		return"profesor/edit";
	}
	
	@RequestMapping(value = "/profesor/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute Profesor profesor) {
		profesor.setId(id);
		profesoresService.addProfesor(profesor);
		return "redirect:/profesor/details/"+id;
	}
	
}
