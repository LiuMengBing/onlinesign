package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebSocket
@SpringBootApplication
@EnableAutoConfiguration
@EnableSwagger2
public class OnlinesignApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinesignApplication.class, args);
	}
}
