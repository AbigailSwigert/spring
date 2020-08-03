package com.tekcamp.apiExercises.repository;

import com.tekcamp.apiExercises.model.User;
import com.tekcamp.apiExercises.model.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, PagingAndSortingRepository<User, Long> {
    User findByUserId(String userId);

    User findByEmail(String email);
}
