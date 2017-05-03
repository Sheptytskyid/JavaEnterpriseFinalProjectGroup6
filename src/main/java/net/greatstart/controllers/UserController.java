package net.greatstart.controllers;

import net.greatstart.dto.DtoUser;
import net.greatstart.model.User;
import net.greatstart.services.UserConverterService;
import net.greatstart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private UserConverterService userConverter;

    @Autowired
    public UserController(UserService userService, UserConverterService userConverterService) {
        this.userService = userService;
        this.userConverter = userConverterService;
    }

    // TODO error pages (no such user etc.)
    @GetMapping("/{id}")
    public ModelAndView showProfile(@PathVariable long id) {
        User user = userService.getUserById(id);
        ModelAndView modelAndView = new ModelAndView("user/showProfile");
        if (user != null) {
            DtoUser dtoUser = userConverter.fromUserToDto(user);
            modelAndView.addObject(dtoUser);
        }
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editProfile(@PathVariable long id) {
        DtoUser user = userConverter.fromUserToDto(userService.getUserById(id));
        ModelAndView modelAndView = new ModelAndView("user/editProfile");
        modelAndView.addObject(user);
        return modelAndView;
    }

    @PostMapping("/{id}/edit")
    public ModelAndView saveEditedProfile(@PathVariable long id, DtoUser dtoUser, Errors errors) {
        if (errors.hasErrors()) {
            return new ModelAndView("user/editProfile");
        }
        User user = userService.getUserById(id);
        userConverter.updateUserFromDto(user, dtoUser);
        userService.updateUser(user);
        return new ModelAndView("redirect:/user/" + id);
    }

}
