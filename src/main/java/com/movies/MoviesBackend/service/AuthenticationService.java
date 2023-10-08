package com.movies.MoviesBackend.service;


import com.movies.MoviesBackend.dto.UserDto;
import com.movies.MoviesBackend.dto.UserRequest;
import com.movies.MoviesBackend.dto.UserResponse;
import com.movies.MoviesBackend.entities.User;
import com.movies.MoviesBackend.enums.Role;
import com.movies.MoviesBackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserResponse save(UserDto userDto){
        User user = User.builder()
                .userName(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(Role.USER).build();

        userRepository.save(user);

        var token =jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();
    }

    public UserResponse auth(UserRequest userRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
        User user =userRepository.findByUserName(userRequest.getUsername()).orElseThrow();
        String token =jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();
    }
}
