package com.example.schedule.domain.user.repository;

import com.example.schedule.domain.user.entity.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Integer>, UserQueryRepositoryInterface {
    User save(User user);

    User findById(int id);

    void deleteById(int id);
}

interface UserQueryRepositoryInterface {
}


@org.springframework.stereotype.Repository
class UserRepositoryImpl implements UserQueryRepositoryInterface {
}




