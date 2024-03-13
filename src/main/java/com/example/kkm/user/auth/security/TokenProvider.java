package com.example.kkm.user.auth.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.kkm.user.domain.entity.User;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenProvider {

    private final String secrectKey =
        "ZGF5b25lLXNwcmluZy1ib290LWRpdmlkZW5kLXByb2plY3QtdHV0b3JpYWwtand0LXNlY3JldC1rZXkK";

    private static final long TOKEN_EXPIRED_TIME = 1000 * 60 * 60 * 24; // 24hours

    public String generateJwtToken(User user) {
        Date now = new Date();
        Date expiredAt = new Date(now.getTime() + TOKEN_EXPIRED_TIME);

        return JWT.create()
            .withSubject(user.getEmail())
            .withClaim("user_id", user.getId())
            .withIssuedAt(now)
            .withExpiresAt(expiredAt)
            .sign(Algorithm.HMAC512(secrectKey.getBytes()));
    }


}
