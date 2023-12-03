package com.movies.MoviesBackend.service;

import com.movies.MoviesBackend.entities.Movie;
import com.movies.MoviesBackend.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MoviesRepository moviesRepository;


    @Override
    public void saveMovie(Movie movie) {
        moviesRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return moviesRepository.findAll();
    }

    @Override
    public void deleteMovie(Movie movie) {
        moviesRepository.delete(movie);
    }

    @Override
    public Movie getMovieById(String id) throws Exception {
        Optional<Movie> optionalMovie = moviesRepository.findById(id);
        return optionalMovie.orElseThrow(() -> new Exception("Movie not found with id: " + id));
    }
}