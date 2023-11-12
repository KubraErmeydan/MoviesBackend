package com.movies.MoviesBackend.service;

import com.movies.MoviesBackend.dto.ChangePasswordRequest;
import com.movies.MoviesBackend.entities.User;
import com.movies.MoviesBackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public void changePassword(ChangePasswordRequest request, Principal connectedUser){
        var user =(User)( (UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if (!passwordEncoder.matches(request.getCurrentPassword(),user.getPassword())){
            throw new IllegalStateException("Yanlış şifre");
        }

        if (!request.getNewPassword().equals(request.getConfirmationPassword())){
            throw new IllegalStateException("Şifre aynı değil");
        }
    }


}
