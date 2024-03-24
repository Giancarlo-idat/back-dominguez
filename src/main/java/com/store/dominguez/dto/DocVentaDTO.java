package com.store.dominguez.dto;


import com.store.dominguez.dto.base.BaseDTO;
import com.store.dominguez.model.EstadoEnvio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Set;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DocVentaDTO extends BaseDTO {

    private String id;
    private String fechaVenta;
    private ClienteDTO cliente;
    private MetodoPagoDTO metodoPago;
    private TipoTransaccionDTO tipoTransaccion;
    private EstadoEnvio estadoEnvio;
    private String numeroSeguimiento;
    private BigDecimal precio_total;
    private Set<DetalleDocVentaDTO> detalleVenta;

}
