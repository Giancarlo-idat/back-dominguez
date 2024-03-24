package com.store.dominguez.util.validations;

import com.store.dominguez.dto.DocVentaDTO;
import com.store.dominguez.model.EstadoEnvio;
import com.store.dominguez.repository.gestion.ClienteRepository;
import com.store.dominguez.repository.gestion.DocVentaRepository;
import com.store.dominguez.repository.gestion.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DocVentaValidator {

    private final DocVentaRepository docVentaRepository;
    private final ClienteRepository clienteRepository;

    private final MetodoPagoRepository metodoPagoRepository;
    @Autowired
    public DocVentaValidator(DocVentaRepository docVentaRepository, ClienteRepository clienteRepository,MetodoPagoRepository metodoPagoRepository) {
        this.docVentaRepository = docVentaRepository;
        this.clienteRepository = clienteRepository;
        this.metodoPagoRepository = metodoPagoRepository;
    }

    public void validarDocVenta(DocVentaDTO docVentaDTO) {
        validarCamposObligatorios(docVentaDTO);
        validarClienteExistente(docVentaDTO.getCliente().getId());
        validarMetodoPagoExistente(docVentaDTO.getMetodoPago().getId());
        validarEstadoEnvio(docVentaDTO.getEstadoEnvio());
        validarPrecioTotal(docVentaDTO.getPrecio_total());
        validarDetallesVenta(docVentaDTO);
    }

    private void validarCamposObligatorios(DocVentaDTO docVentaDTO) {
        if (Validations.isBlank(docVentaDTO.getId())
                || docVentaDTO.getFechaVenta() == null
                || Validations.isBlank(docVentaDTO.getCliente().getId())
                || Validations.isBlank(docVentaDTO.getMetodoPago().getId())
                || docVentaDTO.getTipoTransaccion() == null) {
            throw new IllegalArgumentException("Todos los campos son obligatorios para crear una venta");
        }
    }

    private void validarClienteExistente(String idCliente) {
        if (!clienteRepository.existsById(idCliente)) {
            throw new IllegalArgumentException("El cliente especificado no existe");
        }
    }

    private void validarMetodoPagoExistente(String idMetodoPago) {
        if (!metodoPagoRepository.existsMetodoPagoById(idMetodoPago)) {
            throw new IllegalArgumentException("El método de pago especificado no existe");
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
        if (docVentaDTO.getDetalleVenta() == null || docVentaDTO.getDetalleVenta().isEmpty()) {
            throw new IllegalArgumentException("Debe haber al menos un detalle de venta asociado");
        }
    }
}
