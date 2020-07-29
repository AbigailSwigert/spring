package com.tekcamp.apiExercises.service.implementations;

import com.tekcamp.apiExercises.model.User;
import com.tekcamp.apiExercises.repository.UserRepository;
import com.tekcamp.apiExercises.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        List<User> returnValue;
        returnValue = (List<User>) userRepository.findAll();
        return returnValue;
    }

    @Override
    public void createUser(User user) {
//        if (user.getFirstName() == null) {} add error handling later
        userRepository.save(user);
    }

    @Override
    public User getUser(String userId) {
        User returnValue = userRepository.findByUserId(userId);
        return returnValue;
    }
}
