package com.example;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChatbootApplication {

	public static void main(String[] args) {

		//System.out.println("Executando o ChatBoot via SpringBoot");
		SpringApplication.run(ChatbootApplication.class, args);
	}

	/*@Bean
	public ApplicationRunner checkSSL() {
		return args -> {
			System.out.println("Keystore path: " + getClass().getResource("/keystore.p12"));
		};
	}*/

}
