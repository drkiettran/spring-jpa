package com.drkiettran.springjpa;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import com.drkiettran.springjpa.model.Actor;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
class SpringJpaApplicationTests {
	private static final Logger logger = LoggerFactory.getLogger(SpringJpaApplicationTests.class);

	@LocalServerPort
	int randomPort;

	private String baseUrl;
	private RestTemplate restTemplate;
	private HttpHeaders headers;

	@BeforeEach
	void setUp() {
		baseUrl = "http://localhost:" + randomPort;
		restTemplate = new RestTemplate();
		headers = new HttpHeaders();
	}

	@Test
	void testGetOneActor() throws Exception {
		String url = baseUrl + "/actor/1";
		logger.info("url: " + url);
		URI uri = new URI(url);

		headers.set("Content-type", "application/json");

		ResponseEntity<Actor> result = restTemplate.getForEntity(uri, Actor.class);
		logger.info("actor: " + result.getBody());
		assertThat(result.getStatusCodeValue(), is(200));
		assertThat(result.getBody(), is(notNullValue()));
	}

	@Test
	void testGetAllActors() throws URISyntaxException {
		String url = baseUrl + "/actor";
		logger.info("url: " + url);
		URI uri = new URI(url);

		headers.set("Content-type", "application/json");

		ResponseEntity<Actor[]> result = restTemplate.getForEntity(uri, Actor[].class);
		Actor[] actors = result.getBody();
		logger.info("actors: " + actors.length);
		assertThat(result.getStatusCodeValue(), is(200));
		assertThat(actors.length, greaterThan(1));
		assertThat(result.getBody(), is(notNullValue()));

	}

	@Test
	void testGetByLastName() throws URISyntaxException {
		String lastName = "TEMPLE";
		String url = baseUrl + "/actor/last_name/" + lastName;
		logger.info("url: " + url);
		URI uri = new URI(url);

		headers.set("Content-type", "application/json");

		ResponseEntity<Actor[]> result = restTemplate.getForEntity(uri, Actor[].class);
		Actor[] actors = result.getBody();
		logger.info("actors: " + actors.length);
		assertThat(result.getStatusCodeValue(), is(200));
		assertThat(actors.length, greaterThan(1));
		assertThat(result.getBody(), is(notNullValue()));

	}
}
