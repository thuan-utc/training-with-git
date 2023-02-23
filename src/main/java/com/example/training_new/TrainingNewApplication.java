package com.example.training_new;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TrainingNewApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingNewApplication.class, args);
	}

}
