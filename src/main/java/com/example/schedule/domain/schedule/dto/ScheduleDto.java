package com.example.schedule.domain.schedule.dto;

import com.example.schedule.domain.schedule.entity.Schedule;

import java.time.LocalDateTime;

public class ScheduleDto {
    public static class Simple {
        private final int id;
        private final String todo;
        private final String user;
        private final String password;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;

        public Simple(
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

        public Simple(Schedule schedule) {
            this.id = schedule.getId();
            this.todo = schedule.getTodo();
            this.user = schedule.getUser();
            this.password = schedule.getPassword();
            this.createdAt = schedule.getCreatedAt();
            this.updatedAt = schedule.getUpdatedAt();
        }

        public int getId() {
            return id;
        }

        public String getTodo() {
            return todo;
        }

        public String getUser() {
            return user;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public LocalDateTime getUpdatedAt() {
            return updatedAt;
        }
    }

    public static class Create {
        private final String todo;
        private final String user;
        private final String password;

        public Create(
                String todo,
                String user,
                String password
        ) {
            this.todo = todo;
            this.user = user;
            this.password = password;
        }

        public String getTodo() {
            return todo;
        }

        public String getUser() {
            return user;
        }

        public String getPassword() {
            return password;
        }

        public Schedule toEntity() {
            return new Schedule(
                    todo,
                    user,
                    password
            );
        }
    }

    public static class Update {
        private final String todo;
        private final String user;

        public Update(
                String todo,
                String user
        ) {
            this.todo = todo;
            this.user = user;
        }

        public String getTodo() {
            return todo;
        }

        public String getUser() {
            return user;
        }
    }

    public static class Delete {
        private final String password;

        public Delete(String password) {
            this.password = password;
        }

        public String getPassword() {
            return password;
        }
    }
}