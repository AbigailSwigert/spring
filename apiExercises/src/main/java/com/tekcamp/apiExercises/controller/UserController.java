package com.tekcamp.apiExercises.controller;

import com.tekcamp.apiExercises.model.User;
import com.tekcamp.apiExercises.model.request.UserRequest;
import com.tekcamp.apiExercises.model.response.UserResponse;
import com.tekcamp.apiExercises.service.UserService;
import com.tekcamp.apiExercises.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
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

    @GetMapping(path = "/userId={userId}")
    public User getUserByUserId(@PathVariable String userId) {
        User returnValue = userService.getUserByUserId(userId);
        return returnValue;
    }

    @GetMapping(path = "/email={email}")
    public User getUserByEmail(@PathVariable String email) {
        User returnValue = userService.getUserByEmail(email);
        return returnValue;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest, userDto);

        UserDto createdUser = userService.createUser(userDto);

        UserResponse returnValue = new UserResponse();
        BeanUtils.copyProperties(createdUser, returnValue);
        return returnValue;
    }

    @PutMapping
    public void updateUser() {

    }

    @DeleteMapping
    public void deleteUser() {

    }

}
