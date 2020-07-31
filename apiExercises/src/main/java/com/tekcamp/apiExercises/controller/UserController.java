package com.tekcamp.apiExercises.controller;

import com.tekcamp.apiExercises.exceptions.UserServiceException;
import com.tekcamp.apiExercises.model.User;
import com.tekcamp.apiExercises.model.request.UserRequest;
import com.tekcamp.apiExercises.model.response.ErrorMessages;
import com.tekcamp.apiExercises.model.response.UserResponse;
import com.tekcamp.apiExercises.repository.UserRepository;
import com.tekcamp.apiExercises.service.UserService;
import com.tekcamp.apiExercises.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
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

    @PutMapping(path = "/updateUser")
    public UserResponse updateUser(@RequestBody UserRequest userRequest) {
        if (userRequest.getUserId() == null) throw new UserServiceException(ErrorMessages.MISSING_USERID.getErrorMessage());
        if (userRequest.getPassword() == null) throw new UserServiceException(ErrorMessages.MISSING_USER_PASSWORD.getErrorMessage());
        User foundUser = userService.getUserByUserId(userRequest.getUserId());

        UserDto foundUserDto = new UserDto();
        BeanUtils.copyProperties(foundUser, foundUserDto);

        UserDto updatedUser = userService.updateUser(userRequest, foundUserDto);
        BeanUtils.copyProperties(updatedUser, foundUser);

        User storedUserDetails = userRepository.save(foundUser);

        UserResponse returnValue = new UserResponse();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }

    @DeleteMapping(path = "/deleteUser")
    public void deleteUser(@RequestBody UserRequest userRequest) {
        if (userRequest.getUserId() == null) throw new UserServiceException(ErrorMessages.MISSING_USERID.getErrorMessage());
        if (userRequest.getPassword() == null) throw new UserServiceException(ErrorMessages.MISSING_USER_PASSWORD.getErrorMessage());

        User foundUser = userService.getUserByUserId(userRequest.getUserId());

        if(!userRequest.getPassword().equals(foundUser.getPassword())) throw new UserServiceException(ErrorMessages.PASSWORD_INCORRECT.getErrorMessage());

        userRepository.delete(foundUser);

        throw new UserServiceException(ErrorMessages.USER_DELETED.getErrorMessage());
    }

}
