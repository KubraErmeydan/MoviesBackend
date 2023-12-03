package com.movies.MoviesBackend.controller;

import com.movies.MoviesBackend.dto.RegisterRequest;
import com.movies.MoviesBackend.dto.AuthenticationRequest;
import com.movies.MoviesBackend.dto.AuthenticationResponse;
import com.movies.MoviesBackend.repository.UserRepository;
import com.movies.MoviesBackend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;


  //  @PostMapping("/save")
//    public ResponseEntity<AuthenticationResponse> save(@RequestBody UserDto userDto){
//        return ResponseEntity.ok(authenticationService.save(userDto));
//    }
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse> auth(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.auth(request));
    }

//    @PostMapping("/refresh-token")
//    public void refreshToken(
//            HttpServletRequest userRequest,
//            HttpServletResponse userResponse
//    )throws IOException{
//        authenticationService.refreshToken(userRequest,userResponse)
//    }
}
