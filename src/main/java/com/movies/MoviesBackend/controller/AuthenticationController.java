package com.movies.MoviesBackend.controller;

import com.movies.MoviesBackend.dto.UserDto;
import com.movies.MoviesBackend.dto.UserRequest;
import com.movies.MoviesBackend.dto.UserResponse;
import com.movies.MoviesBackend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/save")
    public ResponseEntity<UserResponse> save(@RequestBody UserDto userDto){
        return ResponseEntity.ok(authenticationService.save(userDto));
    }

    @PostMapping("/auth")
    public ResponseEntity<UserResponse> auth(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(authenticationService.auth(userRequest));
    }
}
