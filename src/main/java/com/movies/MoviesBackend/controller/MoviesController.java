package com.movies.MoviesBackend.controller;

import com.movies.MoviesBackend.entities.Movie;
import com.movies.MoviesBackend.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/movies")
public class MoviesController {
    @Autowired
    private MoviesRepository moviesRepository;

    @PostMapping
    public ResponseEntity<?> create (){
        Movie movie= new Movie();

        movie.setTitle("avatar");
        moviesRepository.save(movie);
        return ResponseEntity.ok(movie);
    }
}
