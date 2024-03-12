package com.store.dominguez.service.impl.gestion;

import com.store.dominguez.model.ClienteEntity;
import com.store.dominguez.model.EmpleadoEntity;
import com.store.dominguez.repository.gestion.ClienteRepository;
import com.store.dominguez.repository.gestion.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private EmpleadoRepository empleadoRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Busca al usuario por su email en la base de datos, si no lo encuentra lanza una excepción
        EmpleadoEntity empleadoEntity = empleadoRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("El Usuario " + username + " no existe"));

        String rolNombre = empleadoEntity.getRol().getNombre();

        // Crea una colección de roles para el usuario y obtiene el rol del usuario
        Collection<? extends GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_" + rolNombre));


        // devuelve un objeto UserDetails con la información del usuario y sus roles
        // User obtiene el email y el password del usuario, y los roles que tiene
        // Indicará si la cuenta del usuario está habilitada (true en este caso)
        // Indicará si las credenciales del usuario (contraseña) están habilitadas (true en este caso)
        // Indicará si la cuenta del usuario no ha expirado (true en este caso)
        // Indicará si las credenciales del usuario no han expirado (true en este caso)
        return new User(empleadoEntity.getEmail(), empleadoEntity.getPassword(),
                true,
                true,
                true,
                true,
                authorities); // Colección de roles/permisos del usuario
    }
}
