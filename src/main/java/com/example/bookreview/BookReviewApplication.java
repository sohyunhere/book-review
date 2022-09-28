package com.example.bookreview;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableBatchProcessing
@EnableScheduling
@SpringBootApplication
public class BookReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookReviewApplication.class, args);
	}
}
