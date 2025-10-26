//package com.ecommerce.userservice.domain.usecase.infrastructure.adapter.mysqldb.repository;
//
//import com.ecommerce.userservice.infrastructure.adapter.mysqldb.entity.OrderEntity;
//import com.ecommerce.userservice.infrastructure.adapter.mysqldb.entity.UserEntity;
//import com.ecommerce.userservice.infrastructure.adapter.mysqldb.repository.UserRepositoryJpa;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@ActiveProfiles("test")
//public class UserRepositoryJpaTest {
//
//    @Autowired
//    private UserRepositoryJpa userRepositoryJpa;
//
//    private UserEntity user;
//
//    @BeforeEach
//    void setUp() {
//        OrderEntity order1 = OrderEntity.builder()
//                .orderTrackingNumber("TRACK123")
//                .totalPrice(200.0)
//                .status("PAID")
//                .build();
//
//        OrderEntity order2 = OrderEntity.builder()
//                .orderTrackingNumber("TRACK456")
//                .totalPrice(500.0)
//                .status("SHIPPED")
//                .build();
//
//        user = UserEntity.builder()
//                .documentId("1234567")
//                .name("John")
//                .lastname("Doe")
//                .mobile("3197899685")
//                .email("john@example.com")
//                .roleId(1L)
//                .password("encodedPass")
//                .orders(List.of(order1, order2))
//                .build();
//
//        // Relationship setup
//        order1.setUser(user);
//        order2.setUser(user);
//
//        userRepositoryJpa.save(user);
//    }
//
//    @Test
//    @DisplayName("Should find user by email successfully")
//    void shouldFindUserByEmail() {
//        Optional<UserEntity> result = userRepositoryJpa.findByEmail("john@example.com");
//
//        assertThat(result).isPresent();
//        assertThat(result.get().getEmail()).isEqualTo("john@example.com");
//        assertThat(result.get().getOrders()).hasSize(2);
//    }
//
//    @Test
//    @DisplayName("Should return empty when user not found by email")
//    void shouldReturnEmptyWhenUserNotFoundByEmail() {
//        Optional<UserEntity> result = userRepositoryJpa.findByEmail("notfound@example.com");
//        assertThat(result).isEmpty();
//    }
//
//    @Test
//    @DisplayName("Should find user with orders using findByIdWithOrders query")
//    void shouldFindUserWithOrders() {
//        Optional<UserEntity> result = userRepositoryJpa.findByIdWithOrders(user.getId());
//
//        assertThat(result).isPresent();
//        UserEntity foundUser = result.get();
//
//        assertThat(foundUser.getEmail()).isEqualTo("john@example.com");
//        assertThat(foundUser.getOrders()).isNotEmpty();
//        assertThat(foundUser.getOrders().get(0).getOrderTrackingNumber()).isEqualTo("TRACK123");
//    }
//
//    @Test
//    @DisplayName("Should return empty when user not found by ID in custom query")
//    void shouldReturnEmptyWhenUserNotFoundById() {
//        Optional<UserEntity> result = userRepositoryJpa.findByIdWithOrders(999L);
//        assertThat(result).isEmpty();
//    }
//}