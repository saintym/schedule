package com.example.schedule.domain.schedule.repository;

import com.example.schedule.domain.schedule.entity.Schedule;
import org.springframework.stereotype.Repository;
import com.querydsl.jpa.JPQLQueryFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleRepository implements ScheduleRepositoryInterface {
    private JPQLQueryFactory queryFactory;


    @Override
    public Schedule save(Schedule schedule) {
        return null;
    }

    @Override
    public Schedule findById(int id) {
        return null;
    }

    @Override
    public List<Schedule> findAll(LocalDateTime start, LocalDateTime end, String keyword) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}