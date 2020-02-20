package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Department;
import com.uniovi.services.DepartmentsService;

@Component
public class AddDepartmentFormValidator implements Validator {

	@SuppressWarnings("unused")
	@Autowired
	private DepartmentsService departmentsService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Department.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		@SuppressWarnings("unused")
		Department department = (Department) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Error.empty");
		

	}

}
