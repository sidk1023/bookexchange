/*package com.example.demo.user;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
 
	@Bean
	CommandLineRunner commandLineRunner(UserRepository repository) {
		return args -> {
			Users sid = new Users("Skoli",
					9866627788l,
					"email@gamil.com",
					"Hyd","passwrd",
					true,
					1000);
	
		
		Users sam = new Users("Sam",
				9866627788l,
				"email2@gamil.com",
				"Hyd","passwrd",
				false,
				100);
		repository.saveAll(
				List.of(sid,sam)
				);
	};
	}
}*/