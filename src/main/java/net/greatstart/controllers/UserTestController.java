package net.greatstart.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserTestController {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

}
