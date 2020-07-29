package com.tekcamp.apiExercises.controller;

import com.tekcamp.apiExercises.model.User;
import com.tekcamp.apiExercises.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        List<User> returnValue = userService.getUsers();
        return returnValue;
    }

    @GetMapping(path = "/{userId}")
    public User getUser(@PathVariable String userId) {
        User returnValue = userService.getUser(userId);
        return returnValue;
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @PutMapping
    public void updateUser() {

    }

    @DeleteMapping
    public void deleteUser() {

    }

}
