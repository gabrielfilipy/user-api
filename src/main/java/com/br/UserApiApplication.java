package com.br;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class UserApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(UserApiApplication.class, args);
	}

}
