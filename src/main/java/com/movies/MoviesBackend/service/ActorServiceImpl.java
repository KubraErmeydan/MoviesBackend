package com.movies.MoviesBackend.service;

import java.util.List;

import com.movies.MoviesBackend.entities.Actor;
import com.movies.MoviesBackend.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ActorServiceImpl implements  ActorService {
    @Autowired
    private ActorRepository actorRepository;

    @Override
    public void saveActor(Actor cast) {
        actorRepository.save(cast);
    }

    @Override
    public List<Actor> getActors() {
        return actorRepository.findAll();
    }

    @Override
    public void deleteActor(String actorId) {
        actorRepository.deleteById(actorId);
    }

    @Override
    public Actor getActorById(String actorId) {
        return actorRepository.findById(actorId).orElse(null);
    }
}
