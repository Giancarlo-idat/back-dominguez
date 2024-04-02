package com.store.dominguez.dto;


import com.store.dominguez.dto.base.BaseDTO;
import com.store.dominguez.model.EstadoEnvio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DocVentaDTO {

    private UUID idVenta;
    private ClienteDTO cliente;

    private String numComprobante;
    private LocalDate fechaEnvio;
    private LocalDate fechaEntrega;

    private TipoTransaccionDTO tipoTransaccion;
    private double impuesto;
    private BigDecimal precioUnitario;
    private BigDecimal precioTotal;
    private EstadoEnvio estadoEnvio;
    private List<DocDetalleVentaDTO> detallesVenta;
    private boolean estado = true;
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    private LocalDateTime fechaActualizacion = LocalDateTime.now();
}
