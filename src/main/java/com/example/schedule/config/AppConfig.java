package com.example.schedule.config;

import com.example.schedule.DatabaseInitializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${sql-file-path}")
    private String sqlFilePath;

    @Bean
    public DatabaseInitializer databaseInitializer() {
        return new DatabaseInitializer(sqlFilePath);
    }


}