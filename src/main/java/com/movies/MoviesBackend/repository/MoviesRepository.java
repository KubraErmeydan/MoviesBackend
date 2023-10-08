package com.movies.MoviesBackend.repository;

import com.movies.MoviesBackend.entities.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends MongoRepository<Movie,String> {

}
