package ua.deliveryservice.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.deliveryservice.domain.User;
import ua.deliveryservice.service.UserService;


/**
 * 
 * Validator class for Register Form input values
 *
 */
@Component
public class UserFormValidator implements Validator {

	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * Checks if current Class object equals to User.class
	 * 
	 * @param clazz Current Class object
	 * @return true, if equals, false - otherwise
	 *
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	/**
	 * 
	 * Validates current object
	 * 
	 * @param target Current object to be validated
	 * @param errors Holds the results of object validation
	 *
	 */
	@Override
	public void validate(Object target, Errors errors) {

		User user = (User)target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.userForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "NotEmpty.userForm.login");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password2", "NotEmpty.userForm.password2");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roles", "NotEmpty.userForm.roles");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.country", "NotEmpty.userForm.address.contry");
		
		if (!user.getPassword().equals(user.getPassword2())) {
			errors.rejectValue("password", "Diff.userForm.password");
		}

		if(user.getLogin() != null && userService.findByLogin(user.getLogin()) != null) {
			errors.rejectValue("login", "Diff.userForm.login");
		}		

	}
	
}
