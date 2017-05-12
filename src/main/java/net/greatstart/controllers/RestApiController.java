package net.greatstart.controllers;

import net.greatstart.model.User;
import net.greatstart.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {
//    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);
//
//    private UserService userService;
//
//    @Autowired
//    public RestApiController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @RequestMapping(value = "/user/", method = RequestMethod.GET)
//    public ResponseEntity<List<User>> listAllUser() {
//        List<User> users = userService.getAllUsers();
//        if (users.isEmpty()) {
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
//    }
//
//    @GetMapping("/user/{id}")
//    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
//        logger.info("Fetching user with id {id}", id);
//        User user = userService.getUserById(id);
//        if (user == null) {
//            logger.error("User with id {id} not found", id);
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<User>(user, HttpStatus.OK);
//    }
//
//    @PostMapping("/user/")
//    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
//        if (userService.isUserExist(user)) {
//            logger.error("Unable to create. A User with name {} already exist", user.getName());
//            return new ResponseEntity(new CustomErrorType("Unable to create. A User with name "
//                    + user.getName() + " already exist."), HttpStatus.CONFLICT);
//        }
//        userService.createUser(user.getEmail(), user.getPassword());
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("api/user/{id}").buildAndExpand(user.getId()).toUri());
//        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
//    }
//
//    @PutMapping(value = "/user/{id}")
//    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
//        logger.info("Updating User with id {}", id);
//
//        User currentUser = userService.findById(id);
//
//        if (currentUser == null) {
//            logger.error("Unable to update. User with id {} not found.", id);
//            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
//                    HttpStatus.NOT_FOUND);
//        }
//
//        currentUser.setName(user.getName());
//        currentUser.setLastName(user.getLastName());
//        currentUser.setEmail(user.getEmail());
//        userService.updateUser(currentUser);
//        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/user/{id}")
//    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
//        logger.info("Fetching & Deleting User with id {}", id);
//
//        User user = userService.findById(id);
//        if (user == null) {
//            logger.error("Unable to delete. User with id {} not found.", id);
//            return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
//                    HttpStatus.NOT_FOUND);
//        }
//        userService.deleteUserById(id);
//        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
//    }
//
//    @DeleteMapping(value = "/user/")
//    public ResponseEntity<User> deleteAllUsers() {
//        logger.info("Deleting All Users");
//        userService.deleteAllUsers();
//        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
//    }
}
