package com.emeims.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JwtUtils{

    private static final String SIGN = "!token*($^HJKHKJHSAF(@dd";

    /*生成token令牌*/
    public static String getToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String Token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGN));
        return Token;
    }

    /*验证token令牌是否合法*/
    public static void verifyToken(String token){
      JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }


    /*获取token信息*/
    public static DecodedJWT getTokenInfo(String token){
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }
}
