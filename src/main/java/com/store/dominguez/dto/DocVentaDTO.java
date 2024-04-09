package com.store.dominguez.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class DocVentaDTO {

    private String idVenta;
    private ClienteDTO cliente;

    private String numComprobante;
    private LocalDate fechaEnvio;
    private LocalDate fechaEntrega;

    private TipoTransaccionDTO tipoTransaccion;


    private BigDecimal precioTotal;
    private BigDecimal opGravadas;
    private EstadoEnvio estadoEnvio;
    private BigDecimal igv;
    private boolean estado = true;


    private List<DocDetalleVentaDTO> detallesVenta;
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    private LocalDateTime fechaActualizacion = LocalDateTime.now();
}
