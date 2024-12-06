package com.example.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class ScheduleApplication implements CommandLineRunner {

	@Autowired
	private DatabaseInitializer databaseInitializer;

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(ScheduleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try (Connection connection = dataSource.getConnection()) {
			databaseInitializer.initializeDatabase(connection);
		}
	}
}