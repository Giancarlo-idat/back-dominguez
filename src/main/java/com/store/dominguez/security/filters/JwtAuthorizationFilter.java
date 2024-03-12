package com.store.dominguez.security.filters;

import com.store.dominguez.security.jwt.JwtUtils;
import com.store.dominguez.service.impl.gestion.ClienteServiceImpl;
import com.store.dominguez.service.impl.gestion.UserDetailServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Override
    // Método que se ejecutará en cada solicitud que se haga al servidor
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        // Se obtiene el token JWT del encabezado Authorization de la solicitud
        String tokenHeader = request.getHeader("Authorization");

        // Se verifica si el token existe y si comienza con "Bearer "
        if(tokenHeader != null && tokenHeader.startsWith("Bearer ")){

            // Se extrae el token de la cabecera eliminando el prefijo "Bearer "
            String token = tokenHeader.substring(7);

            // Se verifica si el token es válido
            if(jwtUtils.isTokenValid(token)){

                // Se obtiene el nombre de usuario del token JWT
                String username = jwtUtils.getUsernameFromToken(token);

                // Se obtienen los detalles del usuario
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Se crea un objeto de autenticación con el nombre de usuario y los roles del usuario
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());

                // Se establece la autenticación en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        // Se continúa con la cadena de filtros de seguridad
        filterChain.doFilter(request, response);
    }
}