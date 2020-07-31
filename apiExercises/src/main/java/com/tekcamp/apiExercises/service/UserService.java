package com.tekcamp.apiExercises.service;

import com.tekcamp.apiExercises.model.User;
import com.tekcamp.apiExercises.model.request.UserRequest;
import com.tekcamp.apiExercises.shared.dto.UserDto;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    UserDto createUser(UserDto userDto);

    User getUserByUserId(String userId);

    User getUserByEmail(String email);

    UserDto updateUser(UserRequest userRequest, UserDto userDto);
}
