package com.example.schedule.domain.schedule.entity;

import java.time.LocalDateTime;
import java.util.List;

public class User {
    private int id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<Schedule> schedules;
}
