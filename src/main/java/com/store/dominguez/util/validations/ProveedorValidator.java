package com.store.dominguez.util.validations;


import com.store.dominguez.dto.ProveedorDTO;
import com.store.dominguez.model.TipoDocumentoIdentidadEntity;
import com.store.dominguez.repository.gestion.ProveedorRepository;
import com.store.dominguez.repository.gestion.TipoDocumentoIdentidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProveedorValidator {

    private final ProveedorRepository proveedorRepository;
    private final TipoDocumentoIdentidadRepository tipoDocumentoIdentidadRepository;

    @Autowired
    public ProveedorValidator(ProveedorRepository proveedorRepository,
                              TipoDocumentoIdentidadRepository tipoDocumentoIdentidadRepository) {
        this.proveedorRepository = proveedorRepository;
        this.tipoDocumentoIdentidadRepository = tipoDocumentoIdentidadRepository;
    }

    public void validarProveedor(ProveedorDTO proveedorDTO) {
        validarCamposObligatorios(proveedorDTO);
        validarTipoDocumento(proveedorDTO);
        validarCamposValidos(proveedorDTO);
        validarNumeroDocumentoProveedorExistente(proveedorDTO);
        validarDocumento(proveedorDTO);
    }

    public static void validarTipoDocumento(ProveedorDTO proveedorDTO) {
        if (proveedorDTO.getTipoDocumento().getNombre().equals("DNI")) {
            if (!Validations.isValidDNI(proveedorDTO.getNumeroDocumento()))
                throw new IllegalArgumentException("El DNI no es válido");
        } else if (proveedorDTO.getTipoDocumento().getNombre().equals("RUC")) {
            if (!Validations.isValidRUC(proveedorDTO.getNumeroDocumento()))
                throw new IllegalArgumentException("El RUC no es válido");
        } else {
            throw new IllegalArgumentException("El tipo de documento no es válido");
        }
    }

    private static void validarCamposValidos(ProveedorDTO proveedorDTO) {
        if (!Validations.isValidNames(proveedorDTO.getNombres()))
            throw new IllegalArgumentException("El nombre no es válido");
        if (!Validations.isValidEmail(proveedorDTO.getEmail()))
            throw new IllegalArgumentException("El email no es válido");
        if (Validations.isValidTelephone(proveedorDTO.getTelefono()))
            throw new IllegalArgumentException("El teléfono no es válido");

    }

    public static void validarCamposObligatorios(ProveedorDTO proveedorDTO) {
        if (Validations.isBlank(proveedorDTO.getNombres()))
            throw new NullPointerException("El campo nombre no puede ser nulo");
        if (Validations.isBlank(proveedorDTO.getEmail()))
            throw new IllegalArgumentException("El campo email no puede ser nulo");
    }

    public void validarDocumento(ProveedorDTO proveedorDTO) {
        Optional<TipoDocumentoIdentidadEntity> documento = tipoDocumentoIdentidadRepository.findById(proveedorDTO.getTipoDocumento().getId());
        if (documento.isEmpty())
            throw new Exceptions.TipoDocumentoInvalidoException("El tipo de documento no existe");

        if (documento.get().getId().isBlank())
            throw new Exceptions.TipoDocumentoInvalidoException("El tipo de documento no puede estar vacio ");

        if (!documento.get().isEstado())
            throw new Exceptions.TipoDocumentoInvalidoException("El tipo de documento no esta habilitado ");

        if (!proveedorDTO.getTipoDocumento().getNombre().trim().equalsIgnoreCase(documento.get().getNombre().trim()))
            throw new Exceptions.TipoDocumentoInvalidoException("El nombre del tipo de documento no coincide con el tipo de documento seleccionado");
    }

    public void validarNumeroDocumentoProveedorExistente(ProveedorDTO proveedorDTO) {

        if (proveedorDTO.getNumeroDocumento() == null)
            throw new NullPointerException("El número de documento no puede ser nulo");


        if (proveedorRepository.existsByNumeroDocumento(proveedorDTO.getNumeroDocumento()))
            throw new RuntimeException("El número de documento ya existe");

    }

    public static void validarCampoIdProveedor(String proveedorID) {
        if (Validations.isBlank(proveedorID))
            throw new Exceptions.ValidacionException("El id del proveedor no puede estar vacío");
    }
}
