package com.example.schedule.domain.schedule;

import com.example.schedule.domain.schedule.dto.ScheduleDto;
import com.example.schedule.domain.schedule.entity.Schedule;
import com.example.schedule.domain.schedule.repository.ScheduleRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepositoryInterface repository;

    public ScheduleDto.Simple find(int id) {
        return new ScheduleDto.Simple(repository.findById(id));
    }

    public List<ScheduleDto.Simple> findAll(String start, String end, String keyword) {
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;

        if(!start.isEmpty() && !end.isEmpty()) {
            startTime = LocalDateTime.parse(start);
            endTime = LocalDateTime.parse(end);
        }

        var schedules = repository.findAll(startTime, endTime, keyword);

        return schedules.stream()
                .map(ScheduleDto.Simple::new)
                .sorted(Comparator.comparing(ScheduleDto.Simple::getUpdatedAt).reversed())
                .toList();
    }

    public ScheduleDto.Simple create(ScheduleDto.Create dto) {
        var schedule = repository.save(dto.toEntity());
        return new ScheduleDto.Simple(schedule);
    }

    public ScheduleDto.Simple update(int id, ScheduleDto.Update dto) {
        var schedule = repository.findById(id);
        return new ScheduleDto.Simple(partialUpdate(schedule, dto));
    }

    public void delete(int id, ScheduleDto.Delete dto) {
        var schedule = repository.findById(id);
        if(schedule.getPassword().equals(dto.getPassword())) {
            repository.deleteById(id);
        }
    }

    private Schedule partialUpdate(Schedule schedule, ScheduleDto.Update dto) {
        schedule.setTodo(dto.getTodo());
        schedule.setUser(dto.getUser());
        schedule.setUpdatedAt(LocalDateTime.now());
        return repository.save(schedule);
    }
}
