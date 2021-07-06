package com.drkiettran.springjpa.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "ACTOR")
public class Actor {
	@Id
	@Column(name = "actor_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("id")
	private Long actorId;

	@Column(name = "first_name")
	@JsonProperty("first_name")
	private String firstName;

	@Column(name = "last_name")
	@JsonProperty("last_name")
	private String lastName;

	@Column(name = "last_update")
	@JsonProperty("last_update")
	private Timestamp lastUpdate;

	public Actor() {
		super();
	}

	public Actor(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.lastUpdate = Timestamp.valueOf(LocalDateTime.now());
	}

//	public Actor(long actorId, String firstName, String lastName, Timestamp lastUpdate) {
//		super();
//		this.actorId = actorId;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.lastUpdate = lastUpdate;
//	}

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
