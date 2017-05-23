package net.greatstart.controllers;


import net.greatstart.dto.DtoUserProfile;
import net.greatstart.model.User;
import net.greatstart.services.UserConverterService;
import net.greatstart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public DtoUserProfile getUser(Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        DtoUserProfile dtoUser = new DtoUserProfile();
        if (user != null) {
            dtoUser = userConverter.fromUserToDtoProfile(user);
//            return new ResponseEntity<DtoUserProfile>(dtoUser, HttpStatus.OK);
            return dtoUser;
        }
        return dtoUser;
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
    public ResponseEntity<DtoUserProfile> updateUser(@PathVariable("id") long id, @RequestBody DtoUserProfile dtoUser) {
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
