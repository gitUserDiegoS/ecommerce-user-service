package com.ecommerce.userservice.infrastructure.adapter.securityauth;


import com.ecommerce.userservice.domain.model.tokenprovider.TokenProvider;
import com.ecommerce.userservice.domain.model.tokenprovider.gateway.TokenProviderRepository;
import com.ecommerce.userservice.domain.model.user.User;
import com.ecommerce.userservice.domain.model.user.roleenum.RoleEnum;
import com.ecommerce.userservice.domain.model.usersession.UserSession;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Slf4j
@Component
public class JwtTokenProvider implements TokenProviderRepository {

    public static final String EMAIL = "email";
    public static final String ROLE = "role";
    public static final String TYPE = "Bearer";

    private static final String SECRET_KEY = "MySuperSecretKeyThatIsAtLeastThirtyTwoBytesLong123!";
    private static final long EXPIRATION_TIME = 86400000; // 1 día

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    @Override
    public TokenProvider generateToken(User user) {


        return TokenProvider.builder()
                .token(Jwts.builder()
                        .setSubject(user.getId().toString())
                        .claim(ROLE, RoleEnum.fromId(user.getRoleId()).getName())
                        .claim(EMAIL, user.getEmail())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                        .signWith(key, SignatureAlgorithm.HS256)
                        .compact())
                .type(TYPE)
                .expires(EXPIRATION_TIME)
                .build();

    }


    @Override
    public UserSession validateToken(String token) {

        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            log.info("Token validated successfully");

            return new UserSession(
                    Long.valueOf(claims.getSubject()),
                    claims.get(EMAIL, String.class),
                    claims.get(ROLE, String.class)
            );

        } catch (JwtException e) {
            log.error("Error in validateToken method, failed with message: {}", e.getMessage());
            throw new BadCredentialsException("Invalid token");
        }
    }


}

