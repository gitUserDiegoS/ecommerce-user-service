package com.ecommerce.userservice.infrastructure.adapter.mysqldb;


import com.ecommerce.userservice.domain.model.user.User;
import com.ecommerce.userservice.domain.model.user.gateway.UserRepository;
import com.ecommerce.userservice.infrastructure.adapter.mysqldb.entity.UserEntity;
import com.ecommerce.userservice.infrastructure.adapter.mysqldb.mapper.UserMapper;
import com.ecommerce.userservice.infrastructure.adapter.mysqldb.repository.UserRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserRepositoryJpa jpaRepository;

    @Override
    public User register(User user) {
        UserEntity entity = UserMapper.toEntity(user);

        UserEntity saved = jpaRepository.save(entity);

        return UserMapper.toDomain(saved);
    }
}
