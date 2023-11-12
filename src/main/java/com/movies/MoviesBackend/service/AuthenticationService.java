package com.movies.MoviesBackend.service;


import com.movies.MoviesBackend.dto.RegisterRequest;
import com.movies.MoviesBackend.dto.UserDto;
import com.movies.MoviesBackend.dto.AuthenticationRequest;
import com.movies.MoviesBackend.dto.AuthenticationResponse;
import com.movies.MoviesBackend.entities.User;
import com.movies.MoviesBackend.enums.Role;
import com.movies.MoviesBackend.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request){
        var user =User.builder().userName(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                //.role(request.getRole())
                .build();
        user.setRole(Role.USER);
        var savedUser= userRepository.save(user);
        var jwtToken= jwtService.generateToken(user);
        var refreshToken = jwtService.generateToken(user);
        //saveUserToken(savedUser,jwtToken);
        return AuthenticationResponse.builder().refreshToken(refreshToken).build();

    }

    public AuthenticationResponse save(UserDto userDto){
        User user = User.builder()
                .userName(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(Role.USER).build();

        userRepository.save(user);

        var token =jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();
    }

    public AuthenticationResponse auth(AuthenticationRequest userRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword()));
        User user =userRepository.findByUserName(userRequest.getEmail()).orElseThrow();
        String token =jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();
    }

    public void  refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    )throws IOException {
        final String authHeader =request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader==null || !authHeader.startsWith("Bearer")){
            return;
        }
        refreshToken = authHeader.substring(7);
//        userEmail = jwtService.extractUsername(refreshToken);
//        if (userEmail != null) {
//            var user = this.repository.findByEmail(userEmail)
//                    .orElseThrow();
//            if (jwtService.isTokenValid(refreshToken, user)) {
//                var accessToken = jwtService.generateToken(user);
//                revokeAllUserTokens(user);
//                saveUserToken(user, accessToken);
//                var authResponse = AuthenticationResponse.builder()
//                        .accessToken(accessToken)
//                        .refreshToken(refreshToken)
//                        .build();
//                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
//            }
//        }
    }
}
