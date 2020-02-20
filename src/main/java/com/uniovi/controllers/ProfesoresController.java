package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.uniovi.entities.Profesor;
import com.uniovi.services.DepartmentsService;
import com.uniovi.services.ProfesoresService;
import com.uniovi.validators.AddTeacherFormValidator;

@Controller
public class ProfesoresController {

	@Autowired
	private ProfesoresService profesoresService;
	
	@Autowired
	private DepartmentsService departmentsService;
	
	@Autowired
	private AddTeacherFormValidator addTeacherFormValidator;

	@RequestMapping("/profesor/list")
	public String getList(Model model) {
		model.addAttribute("profesoresList", profesoresService.getProfesores());
		return "profesor/list";
	}

	@RequestMapping(value = "/profesor/add", method = RequestMethod.POST)
	public String setProfesor(@Validated Profesor profesor, BindingResult result) {
		addTeacherFormValidator.validate(profesor,result);
		if(result.hasErrors()) {
			return "/profesor/add";
		}
		profesoresService.addProfesor(profesor);
		return "redirect:/profesor/list";
	}

	@RequestMapping("/profesor/add")
	public String getProfesor(Model model) {
		model.addAttribute("departmentList", departmentsService.getDepartments());

		return "profesor/add";
	}

	@RequestMapping(value = "/profesor/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("profesor", new Profesor());
		model.addAttribute("departmentList", departmentsService.getDepartments());

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

	@RequestMapping(value = "/profesor/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute Profesor profesor) {
		profesor.setId(id);
		profesoresService.addProfesor(profesor);
		return "redirect:/profesor/details/" + id;
	}

	@RequestMapping(value = "/profesor/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id) {
		model.addAttribute("profesor", profesoresService.getProfesor(id));
		return "profesor/edit";
	}

}
