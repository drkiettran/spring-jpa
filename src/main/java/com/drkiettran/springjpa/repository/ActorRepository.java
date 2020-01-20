package com.drkiettran.springjpa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.drkiettran.springjpa.model.Actor;

public interface ActorRepository extends CrudRepository<Actor, Long> {
	List<Actor> findByLastName(String lastName);
}
