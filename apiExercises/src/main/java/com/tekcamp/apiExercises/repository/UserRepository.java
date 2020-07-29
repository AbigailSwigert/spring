package com.tekcamp.apiExercises.repository;

import com.tekcamp.apiExercises.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserId(String userId);

    User findByEmail(String email);
}
