package net.greatstart.controllers;

import net.greatstart.dto.DtoUser;
import net.greatstart.services.SecurityService;
import net.greatstart.services.UserService;
import net.greatstart.validators.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/user/register")
public class RegistrationController {
    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private SecurityService securityService;

    @Autowired
    public RegistrationController(PasswordEncoder passwordEncoder, UserService userService,
                                  SecurityService securityService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.securityService = securityService;
    }

    private static final String REGISTRATION_PAGE = "login/registration";

    @GetMapping
    public ModelAndView register() {
        ModelAndView model = new ModelAndView(REGISTRATION_PAGE);
        model.addObject("userForm", new DtoUser());
        return model;
    }

    @PostMapping
    public ModelAndView processRegistration(@Valid @ModelAttribute("userForm") DtoUser user, Errors errors) {
        UserValidationService.validate(user, errors, userService);
        if (errors.hasErrors()) {
            return new ModelAndView(REGISTRATION_PAGE);
        }
        userService.createUser(user.getEmail(), passwordEncoder.encode(user.getPassword()));
        securityService.autoLogin(user.getEmail(), user.getPassword());
        return new ModelAndView("redirect:/");
    }
}
