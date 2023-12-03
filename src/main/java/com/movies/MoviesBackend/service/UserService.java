package com.movies.MoviesBackend.service;

import com.movies.MoviesBackend.dto.ChangePasswordRequest;
import com.movies.MoviesBackend.entities.User;
import com.movies.MoviesBackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username)throws UsernameNotFoundException{
        return userRepository.findByUserName(username)
                .orElseThrow(()-> new UsernameNotFoundException("username not found"));
    }

    public User findById(String id){
        return userRepository.findById(id)
                .orElseThrow(()-> new UsernameNotFoundException("user is not found"));
    }


    public void changePassword(ChangePasswordRequest request, Principal connectedUser){
        var user =(User)( (UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if (!passwordEncoder.matches(request.getCurrentPassword(),user.getPassword())){
            throw new IllegalStateException("Incorrect password");
        }

        if (!request.getNewPassword().equals(request.getConfirmationPassword())){
            throw new IllegalStateException("Passwords do not match");
        }

        String newPasswordEncoded = passwordEncoder.encode(request.getNewPassword());
        user.setPassword(newPasswordEncoded);
        userRepository.save(user);
    }


}
