package com.prep.api;

import com.prep.api.config.property.PrepWebProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(PrepWebProperty.class)
public class PrepWebApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrepWebApiApplication.class, args);
	}

}

