package com.rain.cckfs.oa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rain.cckfs.pojo.Return;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: LightRain
 * @Description: OA认证入口点
 * @DateTime: 2023-04-03 02:34
 * @Version：1.0
 **/
public class OAAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Return notAuthorized = Return.builder().code(401).message("尚未授权").status(Return.FAILED).build();
        out.write(new ObjectMapper().writeValueAsString(notAuthorized));
        out.flush();
        out.close();
    }
}