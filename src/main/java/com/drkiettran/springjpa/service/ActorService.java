package com.drkiettran.springjpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drkiettran.springjpa.model.Actor;
import com.drkiettran.springjpa.repository.ActorRepository;

@Service("actorService")
public class ActorService {
	@Autowired
	private ActorRepository actorRepository;

	public List<Actor> list() {
		return (List<Actor>) actorRepository.findAll();
	}

	public void save(Actor actor) {
		actorRepository.save(actor);
	}

	public void deleteAll() {
		actorRepository.deleteAll();
	}

	public Optional<Actor> findById(Long id) {
		return actorRepository.findById(id);
	}

	public Iterable<Actor> findByLastName(String lastName) {
		return actorRepository.findByLastName(lastName);
	}
}
