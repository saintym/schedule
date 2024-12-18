package com.example.schedule.domain.user.dto;

import com.example.schedule.domain.user.entity.User;

import java.time.LocalDateTime;

public class UserDto {
    public static class Simple {
        public final int id;
        public final String name;
        public final String email;
        public final String password;
        public final LocalDateTime createdAt;
        public final LocalDateTime updatedAt;

        public Simple(
                int id,
                String name,
                String email,
                String password,
                LocalDateTime createdAt,
                LocalDateTime updatedAt
        ) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.password = password;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        public Simple(User user) {
            this.id = user.getId();
            this.name = user.getName();
            this.email = user.getEmail();
            this.password = user.getPassword();
            this.createdAt = user.getCreatedAt();
            this.updatedAt = user.getUpdatedAt();
        }
    }

    public static class Create {
        public final String name;
        public final String email;
        public final String password;

        public Create(
                String name,
                String email,
                String password
        ) {
            this.name = name;
            this.email = email;
            this.password = password;
        }
    }

    public static class Update {
        public final String password;

        public Update(String password) {
            this.password = password;
        }
    }
}