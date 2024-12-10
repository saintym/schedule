package com.example.schedule.domain.user.repository;

import com.example.schedule.domain.user.entity.QUser;
import com.example.schedule.domain.user.entity.User;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Integer>, UserQueryRepositoryInterface {
    User save(User user);
    User findById(int id);
    void deleteById(int id);
}

interface UserQueryRepositoryInterface {
    User findByEmailAndPassword(String email, String password);
}

@org.springframework.stereotype.Repository
class UserRepositoryImpl implements UserQueryRepositoryInterface {
    QUser user = QUser.user;
    private final JPQLQueryFactory queryFactory;

    public UserRepositoryImpl(JPQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }


    @Override
    public User findByEmailAndPassword(String email, String password) {
        return queryFactory
                .selectFrom(user)
                .where(user.email.eq(email))
                .where(user.password.eq(password))
                .fetchOne();
    }
}




