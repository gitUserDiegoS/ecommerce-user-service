package com.ecommerce.userservice.domain.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {

    private Long id;
    private String documentId;
    private String name;
    private String lastname;
    private String mobile;
    private String email;
    private Long roleId;
    private String password;

}
