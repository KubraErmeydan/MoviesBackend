package com.movies.MoviesBackend.entities;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "actors")
public class Actor {

    @Id
    private String id;
    private String name;
    private String imageUrl;
    private Set<Movie> movies;

    public Actor(String name, String imageUrl, Set<Movie> movies) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.movies = movies;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString(){
        return "Actor [id=" + id + ", name=" + name + ", imageUrl=" + imageUrl + "]";
    }
}
