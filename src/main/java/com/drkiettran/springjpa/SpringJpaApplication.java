package com.drkiettran.springjpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJpaApplication {
	private static final Logger logger = LoggerFactory.getLogger(SpringJpaApplication.class);

//	@Autowired
//	private ActorRepository actorRepository;

	public static void main(String[] args) {
		logger.info("Spring JPA app starts ...");
		SpringApplication.run(SpringJpaApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		logger.info("Spring JPA App starts ...");
//
//		System.out.println("\nfindAll()");
//		actorRepository.findAll().forEach(x -> System.out.println(x));
//
//		System.out.println("\nfindById(1L)");
//		actorRepository.findById(1l).ifPresent(x -> System.out.println(x));
//
//		System.out.println("\nfindByLastName('Willis')");
////		actorRepository.findByLastName("Willis").forEach(x -> System.out.println(x));
//	}
}
