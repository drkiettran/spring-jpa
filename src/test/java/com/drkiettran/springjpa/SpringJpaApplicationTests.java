package com.drkiettran.springjpa;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
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

	private String truststore;
	private String keystore;
	private String truststorePassword;
	private String keystorePassword;
	private final String TLS = "TLSv1.2";

	@BeforeEach
	void setUp() throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException,
			CertificateException, MalformedURLException, IOException {
		truststore = System.getenv("CLIENT_TRUSTSTORE");
		keystore = System.getenv("CLIENT_KEYSTORE");
		keystorePassword = System.getenv("CLIENT_KEYSTORE_PASSWORD");
		truststorePassword = System.getenv("CLIENT_TRUSTSTORE_PASSWORD");

		baseUrl = "https://localhost:" + randomPort;
		restTemplate = getRestTemplate();
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

	private RestTemplate getRestTemplate() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException,
			CertificateException, MalformedURLException, IOException, UnrecoverableKeyException {
		char[] truststorePasscode = new char[truststorePassword.length()];
		truststorePassword.getChars(0, truststorePasscode.length, truststorePasscode, 0);
		char[] keystorePasscode = new char[keystorePassword.length()];
		keystorePassword.getChars(0, keystorePasscode.length, keystorePasscode, 0);
		SSLContext sslContext = SSLContextBuilder.create().loadTrustMaterial(new URL(truststore), truststorePasscode)
				.setProtocol(TLS).loadKeyMaterial(new URL(keystore), keystorePasscode, null).setProtocol(TLS).build();
		HttpClient httpClient = HttpClientBuilder.create().setSSLContext(sslContext).build();

		ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

		logger.info("Registered SSL truststore {} for client requests", truststore);
		restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(requestFactory);
		return restTemplate;
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
