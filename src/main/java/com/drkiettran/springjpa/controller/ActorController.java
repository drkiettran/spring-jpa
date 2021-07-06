package com.drkiettran.springjpa.controller;

import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import org.springframework.web.bind.annotation.RestController;

import com.drkiettran.springjpa.model.Actor;
import com.drkiettran.springjpa.service.ActorService;

@RestController
public class ActorController {
	private static final Logger logger = LoggerFactory.getLogger(ActorController.class);
	@Autowired
	private ActorService actorService;

	@GetMapping("/actor")
	Iterable<Actor> findAll(@RequestHeader Map<String, String> headers) {
		headers.forEach((key, value) -> {
			logger.info(String.format("Header '%s' = %s", key, value));
		});
		logger.info("find all actors ...");
		return actorService.list();
	}

	@GetMapping("/actor/{id}")
	Optional<Actor> findOne(@PathVariable Long id) {
		logger.info("find actor by ... {}", id);
		return actorService.findById(id);
	}

	@GetMapping("/actor/last_name/{last_name}")
	Iterable<Actor> findByLastName(@PathVariable(name = "last_name") String lastName) {
		logger.info("find actors by last name ... {}", lastName);
		return actorService.findByLastName(lastName);
	}
}
