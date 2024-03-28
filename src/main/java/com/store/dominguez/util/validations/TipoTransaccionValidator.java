package com.store.dominguez.util.validations;

import com.store.dominguez.dto.TipoTransaccionDTO;
import com.store.dominguez.repository.gestion.TipoTransaccionRepository;
import org.springframework.stereotype.Component;

@Component
public class TipoTransaccionValidator {
    private final TipoTransaccionRepository tipoTransaccionRepository;

    public TipoTransaccionValidator(TipoTransaccionRepository tipoTransaccionRepository) {
        this.tipoTransaccionRepository = tipoTransaccionRepository;
    }

    public void validarTipoTransaccion(TipoTransaccionDTO tipoTransaccionDTO) {
        validarNombreTransaccion(tipoTransaccionDTO.getNombre());
        validarExistenciaTransaccion(tipoTransaccionDTO.getNombre());
    }

    public static void validarNombreTransaccion(String tipoTransaccionDTO) {
        if (Validations.isBlank(tipoTransaccionDTO)) {
            throw new Exceptions.ValidacionException("El nombre de la transacción no puede estar vacío");
        }
    }

    public static void validarIdTransaccion(String tipoTransaccionId) {
        if (Validations.isBlank(tipoTransaccionId)) {
            throw new Exceptions.ValidacionException("El id de la transacción no puede estar vacío");
        }
    }

    public void validarExistenciaTransaccion(String tipoTransaccionDTO) {
        if (tipoTransaccionRepository.existsByNombre(tipoTransaccionDTO))
            throw new RuntimeException("La transacción ya existe");

    }

}
