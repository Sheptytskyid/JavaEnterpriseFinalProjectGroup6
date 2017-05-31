package net.greatstart.controllers;


import net.greatstart.dto.DtoUser;
import net.greatstart.dto.DtoUserProfile;
import net.greatstart.mappers.UserProfileMapper;
import net.greatstart.model.User;
import net.greatstart.services.SecurityService;
import net.greatstart.services.UserService;
import net.greatstart.validators.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class UserController {
    private UserService userService;
    private UserProfileMapper userMapper;
    private SecurityService securityService;
    private PasswordEncoder passwordEncoder;
    private UserValidationService userValidationService;

    @Autowired
    public UserController(UserService userService, UserProfileMapper userMapper,
                          SecurityService securityService,
                          PasswordEncoder passwordEncoder, UserValidationService userValidationService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.securityService = securityService;
        this.passwordEncoder = passwordEncoder;
        this.userValidationService = userValidationService;
    }

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

    @GetMapping("/api/current")
    public ResponseEntity<DtoUserProfile> getUser(Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        if (user != null) {
            DtoUserProfile dtoUser = userMapper.fromUserToDtoProfile(user);
            dtoUser.setInitial(getInitials(dtoUser.getName(), dtoUser.getLastName()));
            return new ResponseEntity<>(dtoUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/api/user/{id}")
    public ResponseEntity<DtoUserProfile> getUserById(@PathVariable("id") long id) {
        DtoUserProfile user = userService.getUserById(id);
        if (user != null) {
            user.setInitial(getInitials(user.getName(), user.getLastName()));
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/api/user/{id}")
    public ResponseEntity<DtoUserProfile> updateUser(
            @PathVariable("id") long id,
            @Valid @RequestBody DtoUserProfile dtoUser) {
        DtoUserProfile currentDtoUser = userService.updateUser(dtoUser, id);
        if (currentDtoUser != null) {
            currentDtoUser.setInitial(getInitials(currentDtoUser.getName(), currentDtoUser.getLastName()));
            return new ResponseEntity<>(currentDtoUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/api/user")
    public ResponseEntity<DtoUser> processRegistration(@Valid @RequestBody DtoUser user) {

        if (userService.getUserByEmail(user.getEmail()) == null) {
            userService.createUser(user.getEmail(), passwordEncoder.encode(user.getPassword()));
            securityService.autoLogin(user.getEmail(), user.getPassword());
            return new ResponseEntity<DtoUser>(HttpStatus.OK);
        }
        return new ResponseEntity<DtoUser>(HttpStatus.CONFLICT);
    }

    private String getInitials(String firstName, String lastName) {
        StringBuilder initials = new StringBuilder();
        if (lastName != null && !lastName.isEmpty()) {
            initials.append(firstName.substring(0, 1).toUpperCase())
                    .append(".")
                    .append(lastName.substring(0, 1).toUpperCase())
                    .append(".");
        } else {
            initials.append(firstName.substring(0, 1).toUpperCase());
        }
        return initials.toString();
    }

}
