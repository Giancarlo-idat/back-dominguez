package com.store.dominguez.security.filters;
// JwtAuthenticationFilter.java

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.dominguez.model.ClienteEntity;
import com.store.dominguez.security.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        ClienteEntity clienteEntity;
        try {
            clienteEntity = new ObjectMapper().readValue(request.getInputStream(), ClienteEntity.class);
        } catch (IOException e) {
            throw new RuntimeException("Error al leer los datos de autenticación", e);
        }

        String email = clienteEntity.getEmail();
        String password = clienteEntity.getPassword();

        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        User user = (User) authResult.getPrincipal();
        String token = jwtUtils.generateAccessToken(user.getUsername(), user.getAuthorities());

        response.addHeader("Authorization", "Bearer " + token);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("token", token);
        httpResponse.put("message", "Autenticación Correcta");
        httpResponse.put("email", user.getUsername());
        httpResponse.put("roles", user.getAuthorities());

        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
    }
}
