package io.github.thedoctorone.sboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.thedoctorone.sboot.Service.FeedbackDataHolder;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	public FeedbackDataHolder feedbackDataHolder() {
		return new FeedbackDataHolder("data", "feedbacks");
	}

}
