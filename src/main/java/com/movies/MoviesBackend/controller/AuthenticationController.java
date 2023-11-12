package com.movies.MoviesBackend.controller;

import com.movies.MoviesBackend.dto.RegisterRequest;
import com.movies.MoviesBackend.dto.UserDto;
import com.movies.MoviesBackend.dto.AuthenticationRequest;
import com.movies.MoviesBackend.dto.AuthenticationResponse;
import com.movies.MoviesBackend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/save")
    public ResponseEntity<AuthenticationResponse> save(@RequestBody UserDto userDto){
        return ResponseEntity.ok(authenticationService.save(userDto));
    }
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse> auth(@RequestBody AuthenticationRequest userRequest){
        return ResponseEntity.ok(authenticationService.auth(userRequest));
    }

//    @PostMapping("/refresh-token")
//    public void refreshToken(
//            HttpServletRequest userRequest,
//            HttpServletResponse userResponse
//    )throws IOException{
//        authenticationService.refreshToken(userRequest,userResponse)
//    }
}
