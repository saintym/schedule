package com.example.schedule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {
    private final String sqlFilePath;

    public DatabaseInitializer(String sqlFilePath) {
        this.sqlFilePath = sqlFilePath;
    }

    public void initializeDatabase(Connection connection) {
        try (BufferedReader reader = new BufferedReader(new FileReader(sqlFilePath));
             Statement statement = connection.createStatement()) {

            StringBuilder sql = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sql.append(line).append("\n");
            }

            statement.execute(sql.toString());
            System.out.println(":::::: Database initialized successfully.");
        } catch (IOException e) {
            System.err.println(":::::: Failed to read SQL file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println(":::::: Failed to initialize database: " + e.getMessage());
        }
    }
}