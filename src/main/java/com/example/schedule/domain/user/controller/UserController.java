package com.example.schedule.domain.user.controller;

import com.example.schedule.domain.user.UserService;
import com.example.schedule.domain.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("{id}")
    public UserDto.Simple find(@PathVariable int id) {
        return service.find(id);
    }

    @PostMapping
    public UserDto.Simple create(@RequestBody UserDto.Create body) {
        return service.create(body);
    }

    @PutMapping("{id}")
    public UserDto.Simple update(@RequestBody UserDto.Update body, @PathVariable int id) {
        return service.update(id, body);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
