package com.vspace.yace.validation;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.vspace.yace.domain.User;

/**
 * <code>Validator</code> for <code>User</code> forms.
 *
 */
public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		System.out.println("validating user...");

		User user = (User)obj;
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "systemIP", "required");
		if (!StringUtils.hasText(user.getAssoId()+"")) {
			errors.rejectValue("assoId", "required", "required");   
		}
		if (!StringUtils.hasLength(user.getfName())) {
			errors.rejectValue("fName", "required", "required"); 
		}
		if (!StringUtils.hasLength(user.getlName())) {
			errors.rejectValue("lName", "required", "required"); 
		}
		if (!StringUtils.hasLength(user.geteMail())) {
			errors.rejectValue("eMail", "required", "required"); 
		}		
		if (!StringUtils.hasLength(user.getSystemIP())) {
			errors.rejectValue("systemIP", "required", "required"); 
		}
	}

	
}
