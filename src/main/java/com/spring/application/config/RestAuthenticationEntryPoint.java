package com.spring.application.config;

import com.spring.application.dto.AuthenticationResponse;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @SneakyThrows
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authenticationException) throws IOException, ServletException {
        AuthenticationResponse authenticationResponse=AuthenticationResponse.builder()
                .title("Access denied")
                .details(authenticationException.getMessage()).build();
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(new JSONObject()
                .put("tittle", "Access denied")
                .put("details", "You must obtain a token to access this page")
                .toString());
    }
}