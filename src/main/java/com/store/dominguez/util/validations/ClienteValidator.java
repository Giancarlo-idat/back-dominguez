package com.store.dominguez.util.validations;

import com.store.dominguez.dto.ClienteDTO;
import com.store.dominguez.dto.ProveedorDTO;
import com.store.dominguez.model.ClienteEntity;
import com.store.dominguez.model.RolEntity;
import com.store.dominguez.model.TipoDocumentoIdentidadEntity;
import com.store.dominguez.model.TipoSexo;
import com.store.dominguez.repository.gestion.RolRepository;
import com.store.dominguez.repository.gestion.TipoDocumentoIdentidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClienteValidator {

    private final TipoDocumentoIdentidadRepository tipoDocumentoIdentidadRepository;
    private final RolRepository rolRepository;

    @Autowired
    public ClienteValidator(TipoDocumentoIdentidadRepository tipoDocumentoIdentidadRepository, RolRepository rolRepository) {
        this.tipoDocumentoIdentidadRepository = tipoDocumentoIdentidadRepository;
        this.rolRepository = rolRepository;
    }

    public void validarCliente(ClienteDTO cliente) {

        validarCamposObligatorios(cliente);
        validarCamposValidos(cliente);
        validarDocumento(cliente);
        validarSexo(cliente.getSexo().name());
       /* validarRol(cliente);*/

    }

    public void validarCamposObligatorios(ClienteDTO clienteDTO) {
        if (Validations.isBlank(clienteDTO.getNombres()))
            throw new IllegalArgumentException("El nombre no puede ser vacío");
        if (Validations.isBlank(clienteDTO.getDireccion()))
            throw new IllegalArgumentException("La dirección no puede ser vacía");
        if (Validations.isBlank(clienteDTO.getTipoDocumento().getNombre()))
            throw new IllegalArgumentException("El tipo de documento no puede ser vacío");
        if (Validations.isBlank(clienteDTO.getSexo().name()))
            throw new IllegalArgumentException("El sexo no puede ser vacío");
        if (Validations.isBlank(clienteDTO.getEmail()))
            throw new IllegalArgumentException("El email no puede ser vacío");
        if (Validations.isBlank(clienteDTO.getTipoDocumento().getId()))
            throw new Exceptions.TipoDocumentoInvalidoException("El tipo de documento no puede estar vacio ");
    }

    public static void validarTipoDocumento(ClienteDTO clienteDTO) {
        if (clienteDTO.getTipoDocumento().getNombre().equals("DNI")) {
            if (!Validations.isValidDNI(clienteDTO.getNumeroDocumento()))
                throw new IllegalArgumentException("El DNI no es válido");
        } else if (clienteDTO.getTipoDocumento().getNombre().equals("RUC")) {
            if (!Validations.isValidRUC(clienteDTO.getNumeroDocumento()))
                throw new IllegalArgumentException("El RUC no es válido");
        } else {
            throw new IllegalArgumentException("El tipo de documento no es válido");
        }
    }

    public void validarCamposValidos(ClienteDTO clienteDTO) {
        if (!Validations.isValidNames(clienteDTO.getNombres()))
            throw new Exceptions.ValidarNombreApellidosException("El nombre no puede contener números");
        if (!Validations.isValidNames(clienteDTO.getApellidos()))
            throw new Exceptions.ValidarNombreApellidosException("El apellido no puede contener números");
        if (!Validations.isValidEmail(clienteDTO.getEmail()))
            throw new Exceptions.EmailInvalidoException("El email debe tener un formato válido");
        if (Validations.isValidTelephone(clienteDTO.getTelefono()))
            throw new Exceptions.ValidarTelefonoException("El teléfono debe tener 9 dígitos");
        if (!Validations.isValidPassword(clienteDTO.getPassword()))
            throw new Exceptions.ValidarPasswordException("La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un número");
    }

    public void validarSexo(String sexo) {
        if (Validations.isBlank(sexo))
            throw new Exceptions.ValidarSexoException("El sexo no puede ser vacío");
        if (!TipoSexo.isValid(sexo))
            throw new Exceptions.ValidarSexoException("El sexo no es válido");
    }

    public void validarDocumento(ClienteDTO clienteDTO) {
        Optional<TipoDocumentoIdentidadEntity> documento = tipoDocumentoIdentidadRepository.findById(clienteDTO.getTipoDocumento().getId());
        if (documento.isEmpty())
            throw new Exceptions.TipoDocumentoInvalidoException("El tipo de documento no existe");

        if (documento.get().getId().isBlank())
            throw new Exceptions.TipoDocumentoInvalidoException("El tipo de documento no puede estar vacio ");

        if (!documento.get().isEstado())
            throw new Exceptions.TipoDocumentoInvalidoException("El tipo de documento no esta habilitado ");

        if (!clienteDTO.getTipoDocumento().getNombre().trim().equalsIgnoreCase(documento.get().getNombre().trim()))
            throw new Exceptions.TipoDocumentoInvalidoException("El nombre del tipo de documento no coincide con el tipo de documento seleccionado");
    }

    /*public void validarRol(ClienteDTO cliente) {
        Optional<RolEntity> roles = rolRepository.findById(cliente.getRol().getId());

        if(cliente.getRol() == null) throw new Exceptions.ValidarRolException("El rol del clienteno puede ser vacio");

        if (roles.isEmpty()) throw new Exceptions.ValidarRolException("El rol no existe");

        if (roles.get().getId().isBlank()) throw new Exceptions.ValidarRolException("El rol no puede estar vacio");

        if (!roles.get().isEstado()) throw new Exceptions.ValidarRolException("El rol no esta habilitado");

        if (!roles.get().getNombre().trim().equalsIgnoreCase(cliente.getRol().getNombre().trim()))
            throw new Exceptions.ValidarRolException("El nombre del rol no coincide con el rol seleccionado");
    }
*/
    public static void validarId(String id) {
        if (Validations.isBlank(id))
            throw new IllegalArgumentException("El id no puede ser vacío");
    }

}
