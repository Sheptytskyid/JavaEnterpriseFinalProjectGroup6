package net.greatstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.greatstart.model.User;
import net.greatstart.services.UserService;
import net.greatstart.validatior.UserValidator;

@Controller
public class AuthenticationController {

	@Autowired
	UserValidator userValidator;
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping( value = "/login" ,method = RequestMethod.GET)
	public ModelAndView loginPage(){
		
		ModelAndView modelObj =  new ModelAndView("login");
		modelObj.addObject("userForm", new User());
		
		return modelObj; 
		
	}
	
	@RequestMapping( value = "/login" ,method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("userForm") User user){
		
		
		ModelAndView modelObj =  new ModelAndView("login");
		modelObj.addObject("userForm", new User());
		
		return modelObj; 
		
	}
	
	
	@RequestMapping( value =  "/registration" ,method = RequestMethod.GET   )
	public ModelAndView registrationPage(){
		
		ModelAndView modelObj =  new ModelAndView("registration");
		modelObj.addObject("userForm", new User());
		return modelObj;
		
	}
	
	@RequestMapping( value =  "/registration" ,method = RequestMethod.POST   )
	public ModelAndView register(@ModelAttribute("userForm") User user, BindingResult bindingResult){
		
		userValidator.validate(user, bindingResult);
		if (bindingResult.hasErrors()){
			return new ModelAndView("registration");
		}
		userService.save(user);
		
		return new ModelAndView("registration");
		
	}
}
