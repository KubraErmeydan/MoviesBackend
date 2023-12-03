package com.movies.MoviesBackend.controller;

import com.movies.MoviesBackend.entities.Review;
import com.movies.MoviesBackend.repository.UserRepository;
import com.movies.MoviesBackend.service.MovieService;
import com.movies.MoviesBackend.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/movies/{movied_id}/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/new/{user_id}")
    public String addNewReview(@PathVariable(value = "movie_id") String movieId,
                               @PathVariable(value = "user_id") String userId,
                               @ModelAttribute("new_review") Review review) {
        try {
            review.setDateTimeMilli(System.currentTimeMillis());
            review.setMovie(movieService.getMovieById(movieId));
            review.setUser(userRepository.findById(userId).orElseThrow());
            reviewService.saveNewReview(review);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/movies/" + movieId;
    }

    @GetMapping("/edit/{review_id}")
    public String editReviewView(@PathVariable(value = "movie_id") String movieId,
                                 @PathVariable(value = "review_id") String reviewId,
                                 Model model, HttpSession session) {
        try {
            Review review = reviewService.getSingleReview(reviewId);
            session.setAttribute("session_review", review);
            model.addAttribute("review", review);
            model.addAttribute("movie", movieService.getMovieById(movieId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user/movie/review/edit_review";
    }

    @PostMapping("/edit/")
    public String editReview(@PathVariable(value = "movie_id") String movieId,
                             @ModelAttribute("review") Review review, HttpSession session) {
        Review tempReview = (Review) session.getAttribute("session_review");
        review.setReviewId(tempReview.getReviewId());
        review.setUser(tempReview.getUser());
        review.setDateTimeMilli(tempReview.getDateTimeMilli());
        review.setLikes(tempReview.getLikes());
        review.setMovie(tempReview.getMovie());

        reviewService.saveNewReview(review);
        return "redirect:/movies/" + movieId;
    }

    @GetMapping("/delete/{review_id}")
    public String deleteReview(@PathVariable(value = "movie_id") String movieId,
                               @PathVariable(value = "review_id") String reviewId) {
        reviewService.deleteReview(reviewId);
        return "redirect:/movies/" + movieId;
    }
}
