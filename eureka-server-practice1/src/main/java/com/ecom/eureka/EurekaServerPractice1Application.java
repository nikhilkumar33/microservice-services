package com.ecom.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerPractice1Application {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerPractice1Application.class, args);
	}

}
