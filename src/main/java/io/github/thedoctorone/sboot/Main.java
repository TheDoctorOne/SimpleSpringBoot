package io.github.thedoctorone.sboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.thedoctorone.sboot.Repository.*;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	public FeedbackRepo feedbackRepo() {
		return new FeedbackRepo("data", "feedbacks");
	}

	@Bean
	public UserRepo userRepo() {
		return new UserRepo("data", "users");
	}

}
