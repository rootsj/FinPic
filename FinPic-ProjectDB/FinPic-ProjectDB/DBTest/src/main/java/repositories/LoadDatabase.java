package repositories;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import usersDTO.Users;

@Configuration
public class LoadDatabase {
	@Bean
	CommandLineRunner initDatabase(UsersRepository repository) {
		return args -> {
			repository.save(Users.builder().userName("cjj").userEmail("asd061").userPassword("1234").build());
		};
	}
}
