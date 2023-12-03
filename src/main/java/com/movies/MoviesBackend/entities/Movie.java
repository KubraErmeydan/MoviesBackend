package com.movies.MoviesBackend.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.*;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    private String id;

    private String name;
    private Date releaseDate;
    private Double rating;
    private String genre;
    private String poster;
    private String description;
    private List<Review> reviews;
    private Set<Actor> actors;
    private Set<User> watchlistUsers;
    private Set<User> favouriteMovieUsers;

    public Movie(String name, Date releaseDate, Double rating, String genre, String poster, String description, Set<Actor> actors, Set<User> watchlistUsers) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.genre = genre;
        this.poster = poster;
        this.description = description;
        this.actors = actors;
        this.watchlistUsers = watchlistUsers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public Set<User> getWatchlistUsers() {
        return watchlistUsers;
    }

    public void setWatchlistUsers(Set<User> watchlistUsers) {
        this.watchlistUsers = watchlistUsers;
    }

    public Set<User> getFavouriteMovieUsers() {
        return favouriteMovieUsers;
    }

    public void setFavouriteMovieUsers(Set<User> favouriteMovieUsers) {
        this.favouriteMovieUsers = favouriteMovieUsers;
    }


    @Override
    public String toString() {
        return "Movie [id=" + id + ", name=" + name + ", releaseDate=" + releaseDate + ", rating=" + rating + ", genre="
                + genre + ", poster=" + poster + ", description=" + description + ", actors=" + actors + "]";
    }


}
