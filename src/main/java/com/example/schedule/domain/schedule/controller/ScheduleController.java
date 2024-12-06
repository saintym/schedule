package com.example.schedule.domain.schedule.controller;

import com.example.schedule.domain.schedule.ScheduleService;
import com.example.schedule.domain.schedule.dto.ScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService service;

    @GetMapping("{id}")
    public ScheduleDto.Simple find(@PathVariable int id) {
        return service.find(id);
    }

    @GetMapping
    public List<ScheduleDto.Simple> findAll(
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end,
            @RequestParam(required = false) String user) {
        return service.findAll(start, end, user);
    }

    @PostMapping
    public ScheduleDto.Simple create(@RequestBody ScheduleDto.Create body) {
        return service.create(body);
    }

    @PutMapping("{id}")
    public ScheduleDto.Simple update(@RequestBody ScheduleDto.Update body, @PathVariable int id) {
        return service.update(id, body);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id, @RequestBody ScheduleDto.Delete body) {
        service.delete(id, body);
    }
}
