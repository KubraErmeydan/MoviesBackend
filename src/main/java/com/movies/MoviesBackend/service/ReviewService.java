package com.movies.MoviesBackend.service;

import com.movies.MoviesBackend.entities.Review;

import java.util.List;

public interface ReviewService {
    void saveNewReview(Review review);

    void deleteReview(String id);

    Review getSingleReview(String id);
}