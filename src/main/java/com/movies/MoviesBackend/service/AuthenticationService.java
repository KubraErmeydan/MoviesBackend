package com.movies.MoviesBackend.service;


import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ArrayList<String> tokens = new ArrayList<>();



    public AuthenticationResponse register(RegisterRequest request){
        var user =User.builder()
                .userName(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        var refreshToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().refreshToken(refreshToken).build();

    }

    public AuthenticationResponse auth(AuthenticationRequest userRequest) {
        User user = userRepository.findByEmail(userRequest.getEmail()).orElseThrow();
        final var userToken = new UsernamePasswordAuthenticationToken(user.getUsername(), userRequest.getPassword());
        authenticationManager.authenticate(userToken);

        var token = jwtService.generateToken(user);
        tokens.add(token);

        return AuthenticationResponse.builder().token(token).build();
    }

    public boolean containsToken (String token){
        return tokens.contains(token);
    }



//    public void  refreshToken(HttpServletRequest request, HttpServletResponse response
//    )throws IOException {
//        final String authHeader =request.getHeader(HttpHeaders.AUTHORIZATION);
//
//        if (authHeader==null || !authHeader.startsWith("Bearer")){
//            return;
//        }
//        String refreshToken = authHeader.substring(7);
//        String userEmail = jwtService.extractUsername(refreshToken);
//        if (userEmail != null) {
//            User user = this.userRepository.findByUserName(userEmail)
//                    .orElseThrow(()-> new UsernameNotFoundException("User not found"));
//            if (jwtService.isTokenValid(refreshToken, user)) {
//                String accessToken = jwtService.generateToken(user);
//                revokeAllUserTokens(user);
//                saveUserToken(user, accessToken);
//                AuthenticationResponse authResponse = AuthenticationResponse.builder()
//                        .accessToken(accessToken)
//                        .refreshToken(refreshToken)
//                        .build();
//                response.getWriter().write(new ObjectMapper().writeValueAsString(authResponse));
//                response.setContentType("application/json");
//                response.setStatus(HttpServletResponse.SC_OK);
//            }
//        }
//    }
}
