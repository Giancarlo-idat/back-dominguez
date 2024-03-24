package com.store.dominguez.security.filters;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.dominguez.model.ClienteEntity;
import com.store.dominguez.repository.gestion.ClienteRepository;
import com.store.dominguez.security.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


// Filtro para autenticar al usuario por JWT
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    // Método para intentar autenticar al usuario
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        ClienteEntity clienteEntity = null;
        String email = "";
        String password = "";

        try {
            // Lee los datos de autenticación del cuerpo de la solicitud
            clienteEntity = new ObjectMapper().readValue(request.getInputStream(), ClienteEntity.class);
            email = clienteEntity.getEmail();
            password = clienteEntity.getPassword();
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Crea el token de autenticación tomando los datos del email y password
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);

        // Devuelve la autenticación a AuthenticationManager para que sea verificada y si es correcta
        return getAuthenticationManager().authenticate(authenticationToken);
    }


    // Método llamado cuando la autenticación es exitosa
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        // Obtiene el usuario autenticado
        User user = (User) authResult.getPrincipal();

        // Genera el token de acceso
        String token = jwtUtils.generateAccessToken(user.getUsername(),user.getAuthorities());

        System.out.println("user: " + user);

        // Agraga el token al encabezado de la respuesta
        response.addHeader("Authorization", token);

        // Se crea un objeto JSON para enviarlo como respuesta
        Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("token", token);
        httpResponse.put("message", "Autenticación Correcta");
        httpResponse.put("email", user.getUsername());
        httpResponse.put("roles", user.getAuthorities());


        // Se escribe el objeto JSON en el cuerpo de la respuesta
        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();

        // Continua con la cadena de filtros
        super.successfulAuthentication(request, response, chain, authResult);
    }
}