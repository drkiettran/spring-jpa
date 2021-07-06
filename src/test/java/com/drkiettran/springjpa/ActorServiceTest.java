package com.drkiettran.springjpa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.drkiettran.springjpa.model.Actor;
import com.drkiettran.springjpa.service.ActorService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ActorServiceTest {
	@Autowired
	private ActorService actorService;

	@Before
	public void setUp() {
		actorService.deleteAll();
	}

	@After
	public void tearDown() {
		actorService.deleteAll();
	}

	private List<Actor> generateActors(int count) {
		List<Actor> actors = new ArrayList<Actor>();
		for (int i = 0; i < count; i++) {
			actors.add(new Actor("first_name_" + i, "last_name_" + i));
		}
		return actors;
	}

	@Test
	public void whenAppStarts_thenCreatesInitialRecords() {
		List<Actor> genActors = generateActors(3);

		genActors.stream().forEach(x -> {
			actorService.save(x);
		});

		List<Actor> actors = actorService.list();
		assertThat(actors.size(), is(3));
	}
}
