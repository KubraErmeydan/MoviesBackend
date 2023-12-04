package com.movies.MoviesBackend.config;


import com.movies.MoviesBackend.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader("Authorization");
        final String jwt;
        final String username;

        if (header ==null || header.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        jwt=header.substring(7);
        username=jwtService.extractUserName(jwt);

        if (username !=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails= userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(jwt,userDetails)){
                SecurityContext context = SecurityContextHolder.createEmptyContext();

                UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(context);

            }
            filterChain.doFilter(request,response);
        }
    }
}
