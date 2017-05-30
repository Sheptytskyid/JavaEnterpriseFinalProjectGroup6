package net.greatstart.controllers;


import net.greatstart.dto.DtoUserProfile;
import net.greatstart.mappers.UserProfileMapper;
import net.greatstart.model.User;
import net.greatstart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class UserController {
    private UserService userService;
    private UserProfileMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserProfileMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
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
            return new ResponseEntity<>(dtoUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/user")
    public List<User> getAllUsers() {
       return userService.getAllUsers();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/api/user/{id}")
    public ResponseEntity<DtoUserProfile> getUserById(@PathVariable("id") long id) {
        DtoUserProfile user = userService.getUserById(id);
        if (user != null) {
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
            return new ResponseEntity<>(currentDtoUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
