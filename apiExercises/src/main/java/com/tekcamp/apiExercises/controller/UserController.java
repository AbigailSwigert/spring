package com.tekcamp.apiExercises.controller;

import com.tekcamp.apiExercises.model.User;
import com.tekcamp.apiExercises.model.request.UserRequest;
import com.tekcamp.apiExercises.model.response.UserResponse;
import com.tekcamp.apiExercises.service.UserService;
import com.tekcamp.apiExercises.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ArrayList<UserResponse> getUsers() {
        List<User> userList = userService.getUsers();

        ArrayList<UserResponse> returnValue = new ArrayList<>();

        for (User user : userList) {
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(user,userResponse);
            returnValue.add(userResponse);
        };
        return returnValue;
    }

    @GetMapping(path = "/userId={userId}")
    public UserResponse getUserByUserId(@PathVariable String userId) {
        User queriedUser = userService.getUserByUserId(userId);

        UserResponse returnValue = new UserResponse();
        BeanUtils.copyProperties(queriedUser, returnValue);

        return returnValue;
    }

    @GetMapping(path = "/email={email}")
    public UserResponse getUserByEmail(@PathVariable String email) {
        User queriedUser = userService.getUserByEmail(email.toLowerCase());

        UserResponse returnValue = new UserResponse();
        BeanUtils.copyProperties(queriedUser, returnValue);
        
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
