package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@EnableWebSocket
@SpringBootApplication
@EnableAutoConfiguration
public class OnlinesignApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinesignApplication.class, args);
	}
}
