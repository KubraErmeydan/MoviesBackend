package com.movies.MoviesBackend.service;

import com.movies.MoviesBackend.entities.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    void saveMovie(Movie movie);
    List<Movie> getAllMovies();
    void deleteMovie(Movie movie);
    Movie getMovieById(String id) throws Exception;

}
