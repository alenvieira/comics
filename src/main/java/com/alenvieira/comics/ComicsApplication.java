package com.alenvieira.comics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ComicsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComicsApplication.class, args);
	}

}
