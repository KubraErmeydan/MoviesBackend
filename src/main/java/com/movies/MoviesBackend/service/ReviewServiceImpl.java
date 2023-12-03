package com.movies.MoviesBackend.service;


import com.movies.MoviesBackend.entities.Review;
import com.movies.MoviesBackend.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public void saveNewReview(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public void deleteReview(String id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public Review getSingleReview(String id) {
        return reviewRepository.findById(id).orElse(new Review());
    }
}
