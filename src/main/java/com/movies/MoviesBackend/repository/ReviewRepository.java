package com.movies.MoviesBackend.repository;

import com.movies.MoviesBackend.entities.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review,String> {
}
