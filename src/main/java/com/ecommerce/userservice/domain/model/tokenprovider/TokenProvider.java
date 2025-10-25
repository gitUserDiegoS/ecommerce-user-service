package com.ecommerce.userservice.domain.model.tokenprovider;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TokenProvider {

    String token;

    String type;

    Long expires;

}

