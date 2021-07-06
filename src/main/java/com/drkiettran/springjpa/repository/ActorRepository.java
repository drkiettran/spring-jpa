package com.drkiettran.springjpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.drkiettran.springjpa.model.Actor;

@Repository
public interface ActorRepository extends CrudRepository<Actor, Long> {
	@Query("SELECT a FROM Actor a WHERE a.lastName = ?1")
	Iterable<Actor> findByLastName(String lastName);
}
