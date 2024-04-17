package com.store.dominguez.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocDetalleVentaDTO {

    private UUID id;
    private ProductoDTO productos;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal precioTotal;

    @Builder.Default
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @Builder.Default
    private LocalDateTime fechaActualizacion = LocalDateTime.now();
}
