package com.projectmanagementthesis.websecurity;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.projectmanagementthesis.model.User;

@Component
public class CredentialValidator implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		boolean valid = EmailValidator.getInstance().isValid(user.getMail());

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mail", "NotEmpty");
		if (!valid)
			errors.rejectValue("mail", "Invalid.userForm.mail");
		if (userService.findByMail(user.getMail()) != null)
			errors.rejectValue("username", "Duplicate.userForm.mail");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
			errors.rejectValue("password", "Size.userForm.password");
		}

		if (!user.getRegistrationPasswordConfirmation().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
		}

	}

}
