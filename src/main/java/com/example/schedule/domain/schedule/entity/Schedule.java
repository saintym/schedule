package com.example.schedule.domain.schedule.entity;

import java.time.LocalDateTime;

public class Schedule {
    private int id = 0;
    private String todo;
    private String user;
    private String password;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Schedule(
            int id,
            String todo,
            String user,
            String password,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.todo = todo;
        this.user = user;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Schedule(
            String todo,
            String user,
            String password
    ) {
        this.todo = todo;
        this.user = user;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
