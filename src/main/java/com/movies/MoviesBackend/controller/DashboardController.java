package com.movies.MoviesBackend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/resource")
public class DashboardController {
    @GetMapping
    public ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok("Welcome Dashboard");
    }
}
