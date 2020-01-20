package com.drkiettran.springjpa.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.drkiettran.springjpa.model.Actor;
import com.drkiettran.springjpa.repository.ActorRepository;

@RestController
public class ActorController {
	private static final Logger logger = LoggerFactory.getLogger(ActorController.class);
	@Autowired
	private ActorRepository actorRepository;

	@GetMapping("/actor")
	Iterable<Actor> findAll() {
		logger.info("find all actors ...");
		return actorRepository.findAll();
	}

	@GetMapping("/actor/{id}")
	Optional<Actor> findOne(@PathVariable Long id) {
		logger.info("find actor by ... {}", id);
		return actorRepository.findById(id);
	}

	@GetMapping("/actor/last_name/{last_name}")
	Iterable<Actor> findByLastName(@PathVariable String last_name) {
		logger.info("find actors by last name ... {}", last_name);
		return actorRepository.findByLastName(last_name);
	}
}
