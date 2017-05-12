package net.greatstart.controllers;

import net.greatstart.dto.DtoUserProfile;
import net.greatstart.model.User;
import net.greatstart.services.ImageResizer;
import net.greatstart.services.UserConverterService;
import net.greatstart.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final String SHOW_PROFILE = "user/UserPage";
    private static final String EDIT_PROFILE = "user/EditUserPage";
    private static final String ERROR_PROFILE = "redirect:/";

    private UserService userService;
    private UserConverterService userConverter;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService, UserConverterService userConverterService) {
        this.userService = userService;
        this.userConverter = userConverterService;
    }

    // TODO error pages (no such user etc.); enable admin user to edit profiles
    @GetMapping("/{id}")
    public ModelAndView showProfile(@PathVariable long id) {
        User user = userService.getUserById(id);
        ModelAndView modelAndView = new ModelAndView(SHOW_PROFILE);
        if (user != null) {
            DtoUserProfile dtoUser = userConverter.fromUserToDtoProfile(user);
            modelAndView.addObject(dtoUser);
        }
        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/edit")
    public ModelAndView editProfile(@PathVariable long id, Principal principal) {
        User entityUser = userService.getUserById(id);
        ModelAndView modelAndView = new ModelAndView(EDIT_PROFILE);
        if (entityUser != null && principal != null
                && entityUser.getEmail().equals(principal.getName())) {
            DtoUserProfile user = userConverter.fromUserToDtoProfile(entityUser);
            modelAndView.addObject(user);
        } else {
            modelAndView = new ModelAndView(ERROR_PROFILE);
        }
        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}/edit")
    public ModelAndView saveEditedProfile(@PathVariable long id,
                                          @Valid DtoUserProfile dtoUser,
                                          Errors errors,
                                          Principal principal,
                                          @RequestParam(value = "file", required = false) MultipartFile file) {
        if (errors.hasErrors()) {
            return new ModelAndView(EDIT_PROFILE);
        }
        if (!file.isEmpty()) {
            byte[] content = null;
            try {
                logger.info("File name: " + file.getName());
                logger.info("File size: " + file.getSize());
                logger.info("File content type: " + file.getContentType());
                content = ImageResizer.getImage(file);
                dtoUser.setPhoto(content);
            } catch (IOException e) {
                logger.error("Error saving upload file", e);
            }
            dtoUser.setPhoto(content);
        }
        User currentUser = userService.getUserByEmail(principal.getName());
        if (currentUser != null && id == currentUser.getId()) {
            User entityUser = userService.getUserById(id);
            userConverter.updateUserFromDto(entityUser, dtoUser);
            userService.updateUser(entityUser);
        }
        return new ModelAndView("redirect:/user/" + id);
    }

    @GetMapping(value = "/photo/{id}")
    @ResponseBody
    public byte[] downloadPhoto(@PathVariable("id") Long id) {
        DtoUserProfile user = userConverter.fromUserToDtoProfile(userService.getUserById(id));
        return user.getPhoto();
    }


}