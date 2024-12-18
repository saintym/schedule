package com.example.schedule.domain.schedule.repository;

import com.example.schedule.domain.schedule.dto.ScheduleDto;
import com.example.schedule.domain.schedule.entity.QSchedule;
import com.example.schedule.domain.schedule.entity.Schedule;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ScheduleRepository extends Repository<Schedule, Integer>, ScheduleQueryRepositoryInterface {
    Schedule save(Schedule schedule);

    Schedule findById(int id);

    void deleteById(int id);
}

interface ScheduleQueryRepositoryInterface {
    List<ScheduleDto.Simple> findAllByQuery(ScheduleDto.Query query);
}

@org.springframework.stereotype.Repository
class ScheduleRepositoryImpl implements ScheduleQueryRepositoryInterface {

    private final JPQLQueryFactory queryFactory;

    public ScheduleRepositoryImpl(JPQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<ScheduleDto.Simple> findAllByQuery(ScheduleDto.Query query) {
        QSchedule schedule = QSchedule.schedule;

        BooleanBuilder whereClause = new BooleanBuilder();

        if (query.start != null && query.end != null) {
            whereClause.and(schedule.createdAt.between(query.start, query.end));
        }
        if (query.name != null && !query.name.isBlank()) {
            whereClause.and(schedule.title.containsIgnoreCase(query.name));
        }

        return queryFactory
                .selectFrom(schedule)
                .where(whereClause)
                .fetch()
                .stream()
                .map(this::asSimple)
                .toList();
    }

    private ScheduleDto.Simple asSimple(Schedule schedule){
        return new ScheduleDto.Simple(schedule);
    }
}