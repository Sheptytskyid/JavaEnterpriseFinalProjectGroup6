package net.greatstart.controllers;

import net.greatstart.dto.DtoUser;
import net.greatstart.dto.DtoUserProfile;
import net.greatstart.model.User;
import net.greatstart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;

/**
 * A REST controller to handle all {@link net.greatstart.model.User} related
 * requests: create new user, edit user profile, etc.
 */

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

    @Transactional
    @GetMapping("/api/current")
    public ResponseEntity<DtoUserProfile> getUser() {
        DtoUserProfile dtoUser = userService.getCurrentDtoUserProfile();
        if (dtoUser != null) {
            return new ResponseEntity<>(dtoUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Transactional
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/api/user/{id}")
    public ResponseEntity<DtoUserProfile> getUserById(@PathVariable("id") long id) {
        DtoUserProfile user = userService.getDtoUserProfileById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Transactional
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/api/user/{id}")
    public ResponseEntity<DtoUserProfile> updateUser(
            @PathVariable("id") long id,
            @Valid @RequestBody DtoUserProfile dtoUser) {
        DtoUserProfile currentDtoUser = userService.updateUser(dtoUser, id);
        if (currentDtoUser != null) {
            return new ResponseEntity<>(currentDtoUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @Transactional
    @PostMapping("/api/user")
    public ResponseEntity<DtoUser> processRegistration(@Valid @RequestBody DtoUser newUser) {
        User user = userService.createUser(newUser);
        if (user != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

}
