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
    public List<ScheduleDto.Simple> findAll(@ModelAttribute ScheduleDto.Query query) {
        return service.findAll(query);
    }

    @PostMapping
    public ScheduleDto.Simple create(@RequestBody ScheduleDto.Create body) {
        var result = service.create(body);
        return result;
    }

    @PutMapping("{id}")
    public ScheduleDto.Simple update(@RequestBody ScheduleDto.Update body, @PathVariable int id) {
        return service.update(id, body);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
