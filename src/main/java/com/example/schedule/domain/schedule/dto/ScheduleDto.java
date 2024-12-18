package com.example.schedule.domain.schedule.dto;

import com.example.schedule.domain.schedule.entity.Schedule;
import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

public class ScheduleDto {
    public static class Query {
        public final LocalDateTime start;
        public final LocalDateTime end;
        public final String name;

        public Query(
                LocalDateTime start,
                LocalDateTime end,
                String name
        ) {
            this.start = start;
            this.end = end;
            this.name = name;
        }
    }

    public static class Simple {
        public final int id;
        public final String title;
        public final String todo;
        public final int userId;
        public final LocalDateTime createdAt;
        public final LocalDateTime updatedAt;

        @QueryProjection
        public Simple(Schedule schedule) {
            this.id = schedule.getId();
            this.title = schedule.getTitle();
            this.todo = schedule.getTodo();
            this.userId = schedule.getUser().getId();
            this.createdAt = schedule.getCreatedAt();
            this.updatedAt = schedule.getUpdatedAt();
        }
    }

    public static class Create {
        public final String todo;
        public final int userId;
        public final String title;

        public Create(
                String title,
                String todo,
                int userId
        ) {
            this.todo = todo;
            this.title = title;
            this.userId = userId;
        }
    }

    public static class Update {
        public final String todo;
        public final String title;

        public Update(
                String todo,
                String title
        ) {
            this.todo = todo;
            this.title = title;
        }
    }
}