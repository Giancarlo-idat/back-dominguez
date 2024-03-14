package com.store.dominguez;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.store.dominguez.model.*;
import com.store.dominguez.repository.gestion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
public class DominguezApplication {

    public static void main(String[] args) {
        SpringApplication.run(DominguezApplication.class, args);
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    TipoDocumentoIdentidadRepository tipoDocumentoIdentidadRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    RolPermisoRepository rolPermisoRepository;


    /*@Bean
    CommandLineRunner init() {
        return args -> {

            RolEntity rol = RolEntity.builder()
                    .id("ROL-ADM-MIP392")
                    .nombre("Admin")
                    .descripcion("Rol del administrador")
                    .estado(true)
                    .build();

            rol.setFechaCreacion(LocalDateTime.now());
            rolRepository.save(rol);


            RolPermisoEntity rolPermiso = RolPermisoEntity.builder()
                    .id("ROL-ADM-MIP392")
                    .nombre("Admin")
                    .estado(true)
                    .rol(rol)
                    .build();


            rolPermiso.setFechaCreacion(LocalDateTime.now());
            rolPermisoRepository.save(rolPermiso);

            TipoDocumentoIdentidadEntity tipoDocumento = TipoDocumentoIdentidadEntity.builder()
                    .id("TIP-DNI-SDK192")
                    .nombre("DNI")
                    .estado(true)
                    .build();

            TipoDocumentoIdentidadEntity tipoDocumento2 = TipoDocumentoIdentidadEntity.builder()
                    .id("TIP-RUC-IMK292")
                    .nombre("RUC")
                    .estado(true)
                    .build();

            tipoDocumento.setFechaCreacion(LocalDateTime.now());
            tipoDocumento2.setFechaCreacion(LocalDateTime.now());
            tipoDocumentoIdentidadRepository.save(tipoDocumento);
            tipoDocumentoIdentidadRepository.save(tipoDocumento2);

            EmpleadoEntity empleado = EmpleadoEntity.builder()
                    .id("EMP-SUP-OJT512")
                    .nombres("super")
                    .apellidos("admin")
                    .direccion("Calle 100 # 50 - 56")
                    .telefono("122234567")
                    .email("importacionesDominguez2024@gmail.com")
                    .password(passwordEncoder.encode("superadmin123"))
                    .tipoDocumento(tipoDocumento)
                    .rol(rol)
                    .estado(true)
                    .sexo(TipoSexo.MASCULINO)
                    .build();
            empleado.setFechaCreacion(LocalDateTime.now());
            empleadoRepository.save(empleado);

        };
    }
*/
}
