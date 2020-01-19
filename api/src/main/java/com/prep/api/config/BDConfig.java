package com.prep.api.config;
/*
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.prep.api.config.property.PrepWebProperty;

import javax.sql.DataSource;

@Configuration
@Component
public class BDConfig {
	
	//@ConfigurationProperties(prefix = "spring.datasource")
	@Bean
	@Primary
	public DataSource getDataSource() {
		return DataSourceBuilder
				.create()
				//.url("jdbc:mysql://localhost:3306/prepara?createDatabaseIfNotExist=true&useTimezone=true&serverTimezone=UTC")
				.url("jdbc:mysql://prepbm.ddns.net:3306/prepara?createDatabaseIfNotExist=true&useTimezone=true&serverTimezone=UTC")
				.username("root")
				.password("Jc-19971")
				.driverClassName("com.mysql.cj.jdbc.Driver")
				.build();
				
	}
}*/

