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

            String line;
            StringBuilder sql = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sql.append(line).append(" ");

                if (line.trim().endsWith(";")) {
                    statement.execute(sql.toString());
                    sql.setLength(0);
                }
            }

            System.out.println(":::::: Database initialized successfully.");
        } catch (IOException e) {
            System.err.println(":::::: Failed to read SQL file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println(":::::: Failed to initialize database: " + e.getMessage());
        }
    }
}