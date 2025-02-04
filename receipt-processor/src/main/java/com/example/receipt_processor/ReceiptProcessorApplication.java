package com.example.receipt_processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example")
public class ReceiptProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReceiptProcessorApplication.class, args);
	}

}
