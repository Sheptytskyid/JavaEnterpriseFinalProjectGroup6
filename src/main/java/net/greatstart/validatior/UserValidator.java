package net.greatstart.validatior;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import net.greatstart.model.User;
import net.greatstart.services.UserService;


@Component
public class UserValidator implements Validator {
	
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target ;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
		if (user.getName().length() < 4 || user.getName().length() >32 ){
			errors.rejectValue("name", "Size.userForm.username");
		}
//		if (userService.findByName(user.getName())!=null){
//			errors.rejectValue("name", "Duplicate.userForm.username");
//		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");		
		if (user.getPassword().length() < 3 || user.getPassword().length() > 32 ){
			errors.rejectValue("password", "Size.userForm.password");
		}
		
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
		}
	}
}
