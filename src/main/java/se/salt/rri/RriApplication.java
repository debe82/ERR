package se.salt.rri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class RriApplication {

	public static void main(String[] args) {
		SpringApplication.run(RriApplication.class, args);
	}

}
