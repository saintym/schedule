package com.example.schedule.domain.schedule.repository;

import com.example.schedule.domain.schedule.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepositoryInterface {
    Schedule save(Schedule schedule);
    Schedule findById(int id);
    List<Schedule> findAll(LocalDateTime start, LocalDateTime end, String keyword);
    void deleteById(int id);
}
