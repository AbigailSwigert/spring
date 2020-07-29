package com.tekcamp.apiExercises.service.implementations;

import com.tekcamp.apiExercises.model.User;
import com.tekcamp.apiExercises.repository.UserRepository;
import com.tekcamp.apiExercises.service.UserService;
import com.tekcamp.apiExercises.shared.Utils;
import com.tekcamp.apiExercises.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final Utils utils;

    public UserServiceImplementation(UserRepository userRepository, Utils utils) {
        this.userRepository = userRepository;
        this.utils = utils;
    }

    @Override
    public List<User> getUsers() {
        List<User> returnValue;
        returnValue = (List<User>) userRepository.findAll();
        return returnValue;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
//        if (user.getFirstName() == null) {} add error handling later
        User newUser = new User();
        BeanUtils.copyProperties(userDto, newUser);

        newUser.setUserId(utils.generateUserId(30));

        User storedUserDetails = userRepository.save(newUser);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);
        return returnValue;
    }

    @Override
    public User getUser(String userId) {
        User returnValue = userRepository.findByUserId(userId);
        return returnValue;
    }
}
