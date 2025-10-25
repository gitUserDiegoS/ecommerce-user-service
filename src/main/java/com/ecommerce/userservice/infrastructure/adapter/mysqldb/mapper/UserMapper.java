package com.ecommerce.userservice.infrastructure.adapter.mysqldb.mapper;


import com.ecommerce.userservice.domain.model.user.User;
import com.ecommerce.userservice.infrastructure.adapter.mysqldb.entity.UserEntity;

public class UserMapper {

    public static UserEntity toEntity(User user) {
        if (user == null) return null;
        return UserEntity.builder()
                .documentId(user.getDocumentId())
                .name(user.getName())
                .lastname(user.getLastname())
                .mobile(user.getMobile())
                .email(user.getEmail())
                .roleId(user.getRoleId())
                .password(user.getPassword())
                .build();
    }

    public static User toDomain(UserEntity entity) {
        if (entity == null) return null;
        return User.builder()
                .id(entity.getId())
                .documentId(entity.getDocumentId())
                .name(entity.getName())
                .lastname(entity.getLastname())
                .mobile(entity.getMobile())
                .email(entity.getEmail())
                .roleId(entity.getRoleId())
                .password(entity.getPassword())
                .build();
    }
}
