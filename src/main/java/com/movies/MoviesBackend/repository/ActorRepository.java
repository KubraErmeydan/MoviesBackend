package com.movies.MoviesBackend.repository;

import com.movies.MoviesBackend.entities.Actor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActorRepository extends MongoRepository<Actor,String> {
}
