package net.greatstart.controllers;

import net.greatstart.dto.DtoUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * A controller to handle user login. Isn't used with REST.
 */

@Controller
@RequestMapping("/user")
public class LoginController {

    @GetMapping(value = "/login")
    public ModelAndView showLoginForm(String error, String logout) {
        ModelAndView model = new ModelAndView("login/login");
        model.addObject(new DtoUser());
        // TODO localization
        if (error != null) {
            model.addObject("error",
                    "Username or password is incorrect");
        }
        if (logout != null) {
            model.addObject("message",
                    "Logged out successfully");
        }
        return model;
    }

}
