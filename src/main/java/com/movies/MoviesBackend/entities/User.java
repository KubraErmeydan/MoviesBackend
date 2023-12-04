package com.movies.MoviesBackend.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movies.MoviesBackend.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User implements UserDetails {

    @Enumerated(EnumType.STRING)
    Role role;

    @Id
    @GeneratedValue
    private String id;
    @NonNull
    @Indexed(unique = true)
    private String email;
    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull
    @JsonIgnore
    private String password;

//    private String profilePicPath;
//    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

//    @DBRef
//    private Set<Role> roles = new HashSet<>();


    @DBRef
    private Set<Movie> watchListedMovies = new HashSet<>();

    @DBRef
    private Set<Movie> favouriteMovies = new HashSet<>();


    public Set<Movie> getWatchListedMovies() {
        return watchListedMovies;
    }
    public void setWatchListedMovies(Set<Movie> watchListedMovies) {
        this.watchListedMovies = watchListedMovies;
    }

    public void addMovieToWatchlist(Movie movie) {
        watchListedMovies.add(movie);
    }

    public void removeMovieFromWatchlist(String movie_id) {
        watchListedMovies.removeIf(movie -> movie.getId().equals(movie_id));
    }

    public Set<Movie> getFavouriteMovies() {
        return favouriteMovies;
    }

    public void setFavouriteMovies(Set<Movie> favouriteMovies) {
        this.favouriteMovies = favouriteMovies;
    }

    public void addMovieToFavourite(Movie movie) {
        favouriteMovies.add(movie);
    }

    public void removeMovieFromFavourite(String movie_id) {
        favouriteMovies.removeIf(movie -> movie.getId().equals(movie_id));
    }






    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
