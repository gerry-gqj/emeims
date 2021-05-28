package com.emeims.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.emeims.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SignatureException;
import java.util.HashMap;

@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        String token = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")){
                token = cookie.getValue();
            }
        }
        log.info("token:[{}]",token);
        try{
            JwtUtils.verifyToken(token);
            log.info("token签名有效");
            return true;
        } catch (SignatureVerificationException e){
            log.info("token签名无效");
            map.put("msg","无效签名");
            map.put("state","false");
        }
        catch (TokenExpiredException e){
            log.info("token签名已过期");
            map.put("msg","token已过期");
            map.put("state","false");
        }catch (AlgorithmMismatchException e){
            e.printStackTrace();
            map.put("msg","token算法不一致");
            log.info("token签名算法有误");
            map.put("state","false");
        }catch (Exception e){
            map.put("msg","token无效");
            log.info("token无效");
            map.put("state","false");
        }
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
