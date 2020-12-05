package com.lutoke.pond;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"com.lutoke.pond.config", "com.lutoke.pond.setup","com.lutoke.pond.controller", "com.lutoke.pond.security"})
@EnableJpaRepositories(basePackages = { "com.lutoke.pond.repository" })
@EntityScan(basePackages = {"com.lutoke.pond.model"})

public class PondApplication {
	private String reactOrigin;
	public static void main(String[] args) {
		SpringApplication.run(PondApplication.class, args);

	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**").allowedOrigins(reactOrigin);
			}
			
		};
	}

}
