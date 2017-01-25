package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@RestController
//@EnableAutoConfiguration
public class DemoApplication {

//	@RequestMapping("/")
//	String home() {
//		return "Greetings from Spring Boot!";
//	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(WidgetRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Widget("Cyan", 490, 520));
			repository.save(new Widget("Magenta", 500, 530));
			repository.save(new Widget("Yellow", 570, 590));
		};
	}
}
