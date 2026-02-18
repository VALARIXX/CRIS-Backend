package com.cris.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CrisAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrisAuthServiceApplication.class, args);
	}

}