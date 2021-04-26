package com.emeims;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.HashMap;

public class JwtTest {

/*  <dependency>
        <groupId>com.auth0</groupId>
        <artifactId>java-jwt</artifactId>
        <version>3.15.0</version>
    </dependency>*/

    @Test
    public void createJwt(){
        HashMap<String, Object> map = new HashMap<>();


        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,60*10);

        String token = JWT.create()
                /*负荷
                * 注意:请不要将敏感信息存放在token payload里面
                * */
                .withHeader(map)
                .withClaim("name", "YourName")
                .withClaim("password", 123)


                /*签证过期时间*/
                .withExpiresAt(instance.getTime())
                /*认证盐
                * 密钥:请注意不要公开此密钥
                * */
                .sign(Algorithm.HMAC256("toke!#*^$123"));
        System.out.println(token);
    }

    @Test
    public void verificationToken(){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("toke!#*^$123")).build();
        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6MTIzLCJuYW1lIjoiWW91ck5hbWUiLCJleHAiOjE2MTkxNzA5NjZ9.mUsKBOiWG3jWXoL9PM7GcefWLy-Kw9VMwKEgO0AiRr0");
            System.out.println(verify.getHeader());
            System.out.println(verify.getExpiresAt());
            System.out.println(verify.getClaim("name").asString());
            System.out.println(verify.getClaim("password").asInt());
            System.out.println(verify.getSignature());
    }



}
