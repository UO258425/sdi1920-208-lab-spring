package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Profesor;
import com.uniovi.services.MarksService;
import com.uniovi.services.ProfesoresService;

@Component
public class AddTeacherFormValidator implements Validator {

	@Autowired
	private ProfesoresService profesoresService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Profesor.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Profesor profesor = (Profesor) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellidos", "Error.empty");
		if (profesoresService.getProfesorByDni(profesor.getDni()) != null) {
			errors.rejectValue("dni", "Error.signup.dni.duplicate");
		}
		if (profesor.getDni().length() < 10 || !Character.isLetter(profesor.getDni().charAt(profesor.getDni().length()-1))) {
			errors.rejectValue("dni", "Error.profesor.dni.length");
		}
	}

}
