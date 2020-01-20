package com.drkiettran.springjpa.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Actor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long actorId;

	private String firstName;
	private String lastName;
	private Timestamp lastUpdate;

	public Actor() {

	}

	public Actor(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format("{\"%s\":\"%d\"," + "\"%s\":\"%s\"," + "\"%s\":\"%s\"," + "\"%s\":\"%s\"}",

				"actor_id", actorId, "first_name", firstName, "last_name", lastName, "last_update",
				lastUpdate.toString());
	}

	public Long getActorId() {
		return actorId;
	}

	public void setActorId(Long actorId) {
		this.actorId = actorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
