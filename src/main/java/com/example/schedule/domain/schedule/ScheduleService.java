package com.example.schedule.domain.schedule;

import com.example.schedule.domain.schedule.dto.ScheduleDto;
import com.example.schedule.domain.schedule.entity.Schedule;
import com.example.schedule.domain.schedule.repository.ScheduleRepository;
import com.example.schedule.domain.user.entity.User;
import com.example.schedule.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private UserRepository userRepository;


    public ScheduleDto.Simple find(int id) {
        return new ScheduleDto.Simple(scheduleRepository.findById(id));
    }

    public List<ScheduleDto.Simple> findAll(ScheduleDto.Query query) {
        return scheduleRepository.findAllByQuery(query);
    }

    public ScheduleDto.Simple create(ScheduleDto.Create dto) {
        var schedule = toEntity(dto);
        var user = userRepository.findById(dto.userId);

        user.addSchedule(schedule);
        userRepository.save(user);
        schedule = scheduleRepository.save(schedule);

        return new ScheduleDto.Simple(schedule);
    }

    @Transactional
    public ScheduleDto.Simple update(int id, ScheduleDto.Update dto) {
        var schedule = scheduleRepository.findById(id);
        return new ScheduleDto.Simple(partialUpdate(schedule, dto));
    }

    public void delete(int id) {
        var schedule = scheduleRepository.findById(id);
        var user = schedule.getUser();

        user.removeSchedule(schedule);
        userRepository.save(user);

        scheduleRepository.deleteById(id);
    }

    private Schedule partialUpdate(Schedule schedule, ScheduleDto.Update dto) {
        schedule.setTodo(dto.todo);
        schedule.setTitle(dto.title);
        return scheduleRepository.save(schedule);
    }

    private Schedule toEntity(ScheduleDto.Create dto) {
        var user = userRepository.findById(dto.userId);
        return new Schedule(user, dto.title, dto.todo);
    }
}
