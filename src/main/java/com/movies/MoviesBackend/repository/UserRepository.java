package com.movies.MoviesBackend.repository;


import com.movies.MoviesBackend.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUserName(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    //User profile(User principal);
}
