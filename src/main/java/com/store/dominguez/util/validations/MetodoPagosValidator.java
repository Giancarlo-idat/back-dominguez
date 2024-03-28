package com.store.dominguez.util.validations;

import com.store.dominguez.dto.MetodoPagoDTO;
import com.store.dominguez.repository.gestion.MetodoPagoRepository;
import org.springframework.stereotype.Component;

@Component
public class MetodoPagosValidator {

    private final MetodoPagoRepository metodoPagoRepository;

    public MetodoPagosValidator(MetodoPagoRepository metodoPagoRepository) {
        this.metodoPagoRepository = metodoPagoRepository;
    }

    public void validarMetodoPago(MetodoPagoDTO metodoPagoDTO) {
        validarNombreMetodoPago(metodoPagoDTO.getNombre());
        validarExistenciaMetodoPago(metodoPagoDTO.getNombre());
    }

    public static void validarNombreMetodoPago(String metodoPagoDTO) {
        if (Validations.isBlank(metodoPagoDTO)) {
            throw new Exceptions.ValidacionException("El nombre del método de pago no puede estar vacío");
        }
    }

    public static void validarIdMetodoPago(String metodoPagoDTO) {
        if (Validations.isBlank(metodoPagoDTO)) {
            throw new Exceptions.ValidacionException("El id del método de pago no puede estar vacío");
        }
    }

    public void validarExistenciaMetodoPago(String metodoPagoDTO) {
        if (metodoPagoRepository.existsByNombre(metodoPagoDTO))
            throw new RuntimeException("El método de pago ya existe");
    }

}
