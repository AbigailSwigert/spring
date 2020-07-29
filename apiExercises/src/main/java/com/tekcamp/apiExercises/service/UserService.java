package com.tekcamp.apiExercises.service;

import com.tekcamp.apiExercises.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    void createUser(User user);

    User getUser(String userId);
}
