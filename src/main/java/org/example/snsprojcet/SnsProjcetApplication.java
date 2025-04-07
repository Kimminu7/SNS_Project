package org.example.snsprojcet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SnsProjcetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnsProjcetApplication.class, args);
	}

}
