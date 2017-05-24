package net.greatstart.controllers;


import net.greatstart.dto.DtoUserProfile;
import net.greatstart.model.User;
import net.greatstart.services.UserConverterService;
import net.greatstart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class UserRestController {
    private UserService userService;
    private UserConverterService userConverter;

    @Autowired
    public UserRestController(UserService userService, UserConverterService userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

    @GetMapping("/api/current")
    public ResponseEntity<DtoUserProfile> getUser(Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        if (user != null) {
            DtoUserProfile dtoUser = userConverter.fromUserToDtoProfile(user);
            return new ResponseEntity<DtoUserProfile>(dtoUser, HttpStatus.OK);
        }
        return new ResponseEntity<DtoUserProfile>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<DtoUserProfile> getUserById(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            DtoUserProfile dtoUser = userConverter.fromUserToDtoProfile(user);
            return new ResponseEntity<DtoUserProfile>(dtoUser, HttpStatus.OK);
        }
        return new ResponseEntity<DtoUserProfile>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/api/user/{id}")
    public ResponseEntity<DtoUserProfile> updateUser(@PathVariable("id") long id, @Valid @RequestBody DtoUserProfile dtoUser) {
        DtoUserProfile currentDtoUser = new DtoUserProfile();
        User currentUser = userService.getUserById(id);
        if (currentUser != null && id == currentUser.getId()) {
            User entityUser = userService.getUserById(id);
            userConverter.updateUserFromDto(entityUser, dtoUser);
            userService.updateUser(entityUser);
            currentDtoUser = userConverter.fromUserToDtoProfile(entityUser);
        }
        return new ResponseEntity<DtoUserProfile>(currentDtoUser, HttpStatus.OK);
    }

}
