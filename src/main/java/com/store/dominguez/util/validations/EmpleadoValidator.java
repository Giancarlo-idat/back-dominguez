package com.store.dominguez.util.validations;

import com.store.dominguez.dto.EmpleadoDTO;
import com.store.dominguez.model.RolEntity;
import com.store.dominguez.model.TipoDocumentoIdentidadEntity;
import com.store.dominguez.model.TipoSexo;
import com.store.dominguez.repository.gestion.RolRepository;
import com.store.dominguez.repository.gestion.TipoDocumentoIdentidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmpleadoValidator {

    private final TipoDocumentoIdentidadRepository tipoDocumentoIdentidadRepository;
    private final RolRepository rolRepository;

    @Autowired
    public EmpleadoValidator(TipoDocumentoIdentidadRepository tipoDocumentoIdentidadRepository, RolRepository rolRepository) {
        this.tipoDocumentoIdentidadRepository = tipoDocumentoIdentidadRepository;
        this.rolRepository = rolRepository;
    }

    public void validarEmpleado(EmpleadoDTO empleadoDTO) {

        validarCamposObligatorios(empleadoDTO);
        validarCamposValidos(empleadoDTO);
        validarDocumento(empleadoDTO);
        validarSexo(empleadoDTO.getSexo().name());
        validarRol(empleadoDTO);

    }

    public void validarCamposObligatorios(EmpleadoDTO empleadoDTO) {
        if (Validations.isBlank(empleadoDTO.getNombres()))
            throw new IllegalArgumentException("El nombre no puede ser vacío");
        if (Validations.isBlank(empleadoDTO.getDireccion()))
            throw new IllegalArgumentException("La dirección no puede ser vacía");
        if (Validations.isBlank(empleadoDTO.getTipoDocumento().getNombre()))
            throw new IllegalArgumentException("El tipo de documento no puede ser vacío");
        if (Validations.isBlank(empleadoDTO.getSexo().name()))
            throw new IllegalArgumentException("El sexo no puede ser vacío");
        if (Validations.isBlank(empleadoDTO.getEmail()))
            throw new IllegalArgumentException("El email no puede ser vacío");
        if (Validations.isBlank(empleadoDTO.getTipoDocumento().getId()))
            throw new Exceptions.TipoDocumentoInvalidoException("El tipo de documento no puede estar vacio ");
    }

    public void validarCamposValidos(EmpleadoDTO empleadoDTO) {
        if (Validations.isValidNames(empleadoDTO.getNombres()))
            throw new Exceptions.ValidarNombreApellidosException("El nombre no puede contener números");
        if (Validations.isValidNames(empleadoDTO.getApellidos()))
            throw new Exceptions.ValidarNombreApellidosException("El apellido no puede contener números");
        if (Validations.isValidEmail(empleadoDTO.getEmail()))
            throw new Exceptions.EmailInvalidoException("El email debe tener un formato válido");
        if (Validations.isValidTelephone(empleadoDTO.getTelefono()))
            throw new Exceptions.ValidarTelefonoException("El teléfono debe tener 9 dígitos");
        if (Validations.isValidPassword(empleadoDTO.getPassword()))
            throw new Exceptions.ValidarPasswordException("La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un número");
    }

    public void validarSexo(String sexo) {
        if (Validations.isBlank(sexo))
            throw new Exceptions.ValidarSexoException("El sexo no puede ser vacío");
        if (!TipoSexo.isValid(sexo))
            throw new Exceptions.ValidarSexoException("El sexo no es válido");
    }

    public void validarDocumento(EmpleadoDTO empleadoDTO) {
        Optional<TipoDocumentoIdentidadEntity> documento = tipoDocumentoIdentidadRepository.findById(empleadoDTO.getTipoDocumento().getId());
        if (documento.isEmpty())
            throw new Exceptions.TipoDocumentoInvalidoException("El tipo de documento no existe");

        if (documento.get().getId().isBlank())
            throw new Exceptions.TipoDocumentoInvalidoException("El tipo de documento no puede estar vacio ");

        if (!documento.get().isEstado())
            throw new Exceptions.TipoDocumentoInvalidoException("El tipo de documento no esta habilitado ");

        if (!empleadoDTO.getTipoDocumento().getNombre().trim().equalsIgnoreCase(documento.get().getNombre().trim()))
            throw new Exceptions.TipoDocumentoInvalidoException("El nombre del tipo de documento no coincide con el tipo de documento seleccionado");
    }

    public void validarRol(EmpleadoDTO empleadoDTO) {
        Optional<RolEntity> roles = rolRepository.findById(empleadoDTO.getRol().getId());
        if (roles.isEmpty()) throw new Exceptions.ValidarRolException("El rol no existe");

        if (roles.get().getId().isBlank()) throw new Exceptions.ValidarRolException("El rol no puede estar vacio");

        if (!roles.get().isEstado()) throw new Exceptions.ValidarRolException("El rol no esta habilitado");

        if (!roles.get().getNombre().trim().equalsIgnoreCase(empleadoDTO.getRol().getNombre().trim()))
            throw new Exceptions.ValidarRolException("El nombre del rol no coincide con el rol seleccionado");
    }

    public static void validarId(String id) {
        if (Validations.isBlank(id))
            throw new IllegalArgumentException("El id no puede ser vacío");
    }

}
