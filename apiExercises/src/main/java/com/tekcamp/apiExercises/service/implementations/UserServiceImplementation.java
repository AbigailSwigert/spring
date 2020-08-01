package com.tekcamp.apiExercises.service.implementations;

import com.tekcamp.apiExercises.exceptions.UserServiceException;
import com.tekcamp.apiExercises.model.User;
import com.tekcamp.apiExercises.model.request.UserRequest;
import com.tekcamp.apiExercises.model.response.ErrorMessages;
import com.tekcamp.apiExercises.model.response.UserResponse;
import com.tekcamp.apiExercises.repository.UserRepository;
import com.tekcamp.apiExercises.service.UserService;
import com.tekcamp.apiExercises.shared.Utils;
import com.tekcamp.apiExercises.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        if (returnValue.size() < 1) throw new UserServiceException(ErrorMessages.EMPTY_USER_LIST.getErrorMessage());
        return returnValue;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userDto.getFirstName() == null || userDto.getLastName() == null || userDto.getEmail() == null || userDto.getPassword() == null) throw new UserServiceException(ErrorMessages.REQUIRED_FIELD_MISSING.getErrorMessage());
        if (userRepository.findByEmail(userDto.getEmail()) != null) throw new UserServiceException(ErrorMessages.EMAIL_ALREADY_IN_USE.getErrorMessage());
        User newUser = new User();
        BeanUtils.copyProperties(userDto, newUser);

        newUser.setUserId(utils.generateUserId(30));

        User storedUserDetails = userRepository.save(newUser);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);
        return returnValue;
    }

    @Override
    public User getUserByUserId(String userId) {
        User returnValue = userRepository.findByUserId(userId);
        if (returnValue == null) throw new UserServiceException(ErrorMessages.USERID_DOES_NOT_EXIST.getErrorMessage());
        return returnValue;
    }

    @Override
    public User getUserByEmail(String email) {
        User returnValue = userRepository.findByEmail(email);
        if (returnValue == null) throw new UserServiceException(ErrorMessages.USER_EMAIL_DOES_NOT_EXIST.getErrorMessage());
        return returnValue;
    }

    @Override
    public UserDto updateUser(UserRequest userRequest, UserDto userDto) {

        if(!userRequest.getPassword().equals(userDto.getPassword())) throw new UserServiceException(ErrorMessages.PASSWORD_INCORRECT.getErrorMessage());
        if(userRequest.getEmail() == null && userRequest.getFirstName() == null && userRequest.getLastName() == null) throw new UserServiceException(ErrorMessages.NOTHING_TO_UPDATE.getErrorMessage());

        if (userRequest.getFirstName() != null) userDto.setFirstName(userRequest.getFirstName());
        if (userRequest.getLastName() != null) userDto.setLastName(userRequest.getLastName());
        if (userRequest.getEmail() != null) userDto.setEmail(userRequest.getEmail());

        return userDto;
    }

    @Override
    public List<User> getPaginatedUsers(int pageNum, int pageMax) {
        if (getUsers() == null) throw new UserServiceException(ErrorMessages.EMPTY_USER_LIST.getErrorMessage());
        if (pageNum < 1) throw new UserServiceException(ErrorMessages.PAGE_LESS_THAN_ONE.getErrorMessage());
        pageNum--;
        PageRequest paging = PageRequest.of(pageNum, pageMax);
        Page<User> pagedResult = userRepository.findAll(paging);

        List<User> returnValue = pagedResult.toList();
        if (returnValue.size() < 1) throw new UserServiceException(ErrorMessages.PAGE_EMPTY.getErrorMessage());

        return returnValue;
    }
}
