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

    public UserDto.Simple update(int id, UserDto.Update dto) {
        User user = repository.findById(id);
        user = repository.save(user.partialUpdate(dto));
        return new UserDto.Simple(user);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
