package com.example.schedule.domain.user;

import com.example.schedule.domain.user.dto.UserDto;
import com.example.schedule.domain.user.entity.User;
import com.example.schedule.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository repository;

    public UserDto.Simple find(int id) {
        return new UserDto.Simple(repository.findById(id));
    }

    public UserDto.Simple create(UserDto.Create dto) {
        var user = toEntity(dto);
        repository.save(user);

        return new UserDto.Simple(user);
    }

    public UserDto.Simple update(int id, UserDto.Update dto) {
        var user = repository.findById(id);
        return new UserDto.Simple(partialUpdate(user, dto));
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    private User partialUpdate(User user, UserDto.Update dto) {
        user.setEmail(dto.password);
        user.setPassword(dto.password);
        return repository.save(user);
    }

    private User toEntity(UserDto.Create dto) {
        return new User(dto.name, dto.email, dto.password);
    }
}
