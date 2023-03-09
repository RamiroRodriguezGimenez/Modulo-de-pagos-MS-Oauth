package com.serviceoauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EntityScan("com.client.commons.models.entity")
@ComponentScan({"com.client.commons.models.entity", "com.serviceoauth.security","com.serviceoauth.services"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class ServiceOauthApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ServiceOauthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String password="12345";
		for (int i = 0; i < 4; i++) {
			String passwordBCrypt=passwordEncoder.encode(password);
			System.out.println(passwordBCrypt);

		}
	}
}
