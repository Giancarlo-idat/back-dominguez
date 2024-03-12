package com.store.dominguez.util.validations;

import com.store.dominguez.dto.RolDTO;
import com.store.dominguez.repository.gestion.RolRepository;
import org.springframework.stereotype.Component;

@Component
public class RolValidator {

    public final RolRepository rolRepository;

    public void validarRoles(RolDTO rol) {
        validarNombreRol(rol.getNombre());
        validarExistenciaRol(rol.getNombre());
    }

    public RolValidator(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public static void validarNombreRol(String rol) {
        if (Validations.isBlank(rol))
            throw new RuntimeException("El nombre del rol no puede estar vacío");
    }

    public void validarExistenciaRol(String rol) {
        if (rolRepository.existsByNombre(rol))
            throw new RuntimeException("El rol ya existe");
    }

    public static void validarId(String id) {
        if (Validations.isBlank(id))
            throw new RuntimeException("El id del rol no puede estar vacío");
    }

}
