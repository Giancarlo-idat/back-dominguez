package com.store.dominguez.service.impl;

import com.store.dominguez.model.ClienteEntity;
import com.store.dominguez.model.EmpleadoEntity;
import com.store.dominguez.repository.gestion.ClienteRepository;
import com.store.dominguez.repository.gestion.EmpleadoRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final ClienteRepository clienteRepository;
    private final EmpleadoRepository empleadoRepository;

    public UserDetailServiceImpl(ClienteRepository clienteRepository, EmpleadoRepository empleadoRepository) {
        this.clienteRepository = clienteRepository;
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ClienteEntity> clienteEntityOptional = clienteRepository.findByEmail(username);
        if (clienteEntityOptional.isPresent()) {
            ClienteEntity clienteEntity = clienteEntityOptional.get();
            return buildUserDetails(clienteEntity.getEmail(), clienteEntity.getPassword(), clienteEntity.getRol().getNombre(), clienteEntity.getNombres(), clienteEntity.getApellidos());
        }

        Optional<EmpleadoEntity> empleadoEntityOptional = empleadoRepository.findByEmail(username);

        if (empleadoEntityOptional.isPresent()) {
            EmpleadoEntity empleadoEntity = empleadoEntityOptional.get();
            return buildUserDetails(empleadoEntity.getEmail(), empleadoEntity.getPassword(), empleadoEntity.getRol().getNombre(), empleadoEntity.getNombres(), empleadoEntity.getApellidos());
        }

        throw new UsernameNotFoundException("Usuario no encontrado: " + username);

    }

    private UserDetails buildUserDetails(String username, String password, String rolNombre, String nombre, String apellido) {
        Collection<? extends GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_" + rolNombre));
        return User.withUsername(username)
                .password(password)
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
