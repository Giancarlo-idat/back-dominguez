package com.store.dominguez.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.store.dominguez.dto.base.BaseDTO;
import com.store.dominguez.model.enums.EstadoEnvio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class DocVentaDTO extends BaseDTO{

    private String id;
    private ClienteDTO cliente;

    private String numComprobante;
    private LocalDate fechaEnvio;
    private LocalDate fechaEntrega;

    private TipoTransaccionDTO tipoTransaccion;


    private BigDecimal precioTotal;
    private BigDecimal opGravadas;
    private EstadoEnvio estadoEnvio;
    private BigDecimal igv;
    private List<DocDetalleVentaDTO> detallesVenta;

    @Builder.Default
    private boolean estado = true;
    @Builder.Default
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @Builder.Default
    private LocalDateTime fechaActualizacion = LocalDateTime.now();
}
