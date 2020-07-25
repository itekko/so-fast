package com.sofast;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.EnumSet;

public class JwtTest {

    @Test
    public void test01(){
        LocalDateTime dateTime = LocalDateTime.of(2020, 6, 30, 13, 0, 0);
        Algorithm algorithm = Algorithm.HMAC256("secret");
        String token = JWT.create()
                .withIssuer("auth0")
                .sign(algorithm);
        System.out.println(token);

        String token2 = JWT.create()
                .withIssuer("auth0")
                .sign(algorithm);
        System.out.println(token2);
    }


    public void test02(){
        EnumSet.of(RequestMethod.DELETE,RequestMethod.GET);
    }
}
