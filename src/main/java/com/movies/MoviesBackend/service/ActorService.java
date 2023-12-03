package com.movies.MoviesBackend.service;


import com.movies.MoviesBackend.entities.Actor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActorService {
    void saveActor(Actor actor);

    List<Actor> getActors();

    void deleteActor(String actorId);

    Actor getActorById(String actorId) throws Exception;
}
