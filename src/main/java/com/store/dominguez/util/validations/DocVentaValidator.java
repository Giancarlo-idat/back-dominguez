package com.store.dominguez.util.validations;

import com.store.dominguez.dto.DocVentaDTO;
import com.store.dominguez.model.enums.EstadoEnvio;
import com.store.dominguez.repository.gestion.ClienteRepository;
import com.store.dominguez.repository.gestion.DocVentaRepository;
import com.store.dominguez.repository.gestion.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DocVentaValidator {

    private final DocVentaRepository docVentaRepository;
    private final ClienteRepository clienteRepository;
    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public DocVentaValidator(DocVentaRepository docVentaRepository, ClienteRepository clienteRepository, EmpleadoRepository empleadoRepository) {
        this.docVentaRepository = docVentaRepository;
        this.clienteRepository = clienteRepository;
        this.empleadoRepository = empleadoRepository;
    }

    public void validarDocVenta(DocVentaDTO docVentaDTO) {
        validarCamposObligatorios(docVentaDTO);
        validarEstadoEnvio(docVentaDTO.getEstadoEnvio());
        validarClienteExistente(docVentaDTO.getCliente().getId());
        validarDetallesVenta(docVentaDTO);
    }

    private void validarCamposObligatorios(DocVentaDTO docVentaDTO) {
        if (docVentaDTO.getCliente() == null || docVentaDTO.getCliente().getId().isEmpty()) {
            throw new IllegalArgumentException("Todos los campos son obligatorios para crear una venta");
        }
    }


    public void validarClienteExistente(String idCliente) {
        if (!clienteRepository.existsById(idCliente)) {
            throw new IllegalArgumentException("El cliente especificado no existe");
        }
    }

    public void validarEmpleadoExistente(String idEmpleado) {
        if (!empleadoRepository.existsById(idEmpleado)) {
            throw new IllegalArgumentException("El empleado especificado no existe");
        }
    }

    private void validarEstadoEnvio(EstadoEnvio estadoEnvio) {
        if (estadoEnvio != null && !estadoEnvio.equals(EstadoEnvio.PENDIENTE)
                && !estadoEnvio.equals(EstadoEnvio.EN_CAMINO)
                && !estadoEnvio.equals(EstadoEnvio.ENTREGADO)) {
            throw new IllegalArgumentException("El estado de envío especificado no es válido");
        }
    }

    private void validarPrecioTotal(BigDecimal precioTotal) {
        if (precioTotal == null || precioTotal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio total debe ser un número positivo");
        }
    }

    private void validarDetallesVenta(DocVentaDTO docVentaDTO) {
        if (docVentaDTO.getDetallesVenta() == null || docVentaDTO.getDetallesVenta().isEmpty()) {
            throw new IllegalArgumentException("Debe haber al menos un detalle de venta asociado");
        }
    }
}
