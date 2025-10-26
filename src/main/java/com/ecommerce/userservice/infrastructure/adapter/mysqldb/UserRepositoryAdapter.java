package com.ecommerce.userservice.infrastructure.adapter.mysqldb;


import com.ecommerce.userservice.domain.model.user.User;
import com.ecommerce.userservice.domain.model.user.gateway.UserRepository;
import com.ecommerce.userservice.infrastructure.adapter.mysqldb.entity.UserEntity;
import com.ecommerce.userservice.infrastructure.adapter.mysqldb.mapper.UserMapper;
import com.ecommerce.userservice.infrastructure.adapter.mysqldb.repository.UserRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Adapter MySql that supports queries to the database
 */
@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserRepositoryJpa jpaRepository;

    /**
     * Register a {@link User}
     *
     * @param user that would be created
     * @return User Created
     */
    @Override
    public User register(User user) {

        UserEntity entity = UserMapper.toEntity(user);

        UserEntity saved = jpaRepository.save(entity);

        return UserMapper.toDomain(saved);
    }

    /**
     * Used to get {@link User} data by email
     *
     * @param email used for search user data
     * @return user's data
     */
    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> byEmail = jpaRepository.findByEmail(email);

        if (byEmail.isPresent()) {
            User user = UserMapper.toDomain(byEmail.get());
            return Optional.of(user);
        } else {
            return Optional.empty();
        }

    }

    /**
     * Retrieves {@link User} data with placed order
     *
     * @param userId used to search data
     * @return user's data
     */
    @Override
    public Optional<User> findByIdWithOrders(Long userId) {
        Optional<UserEntity> userByIdWithOrders = jpaRepository.findByIdWithOrders(userId);

        if (userByIdWithOrders.isPresent()) {
            User user = UserMapper.toDomain(userByIdWithOrders.get());
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }
}
