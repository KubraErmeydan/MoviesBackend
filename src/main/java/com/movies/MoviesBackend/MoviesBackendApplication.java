package com.movies.MoviesBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })

public class MoviesBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(MoviesBackendApplication.class, args);
	}

}
