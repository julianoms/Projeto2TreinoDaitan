package br.com.project.crud_mongo.controllers;

import br.com.project.crud_mongo.models.User;
import br.com.project.crud_mongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/signin")
    public String login(//
                        @RequestParam String username, //
                        @RequestParam String password) {
        return userService.signin(username, password);
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        return userService.signup(user);
    }
}