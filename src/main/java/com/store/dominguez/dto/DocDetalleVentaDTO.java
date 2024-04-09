package com.store.dominguez.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DocDetalleVentaDTO {

    private UUID idDetalleVenta;
    private DocVentaDTO venta;
    private ProductoDTO productos;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal precioTotal;

    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private LocalDateTime fechaActualizacion = LocalDateTime.now();
}
