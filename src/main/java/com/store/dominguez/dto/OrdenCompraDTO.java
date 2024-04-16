package com.store.dominguez.dto;


import com.store.dominguez.dto.base.BaseDTO;
import lombok.AllArgsConstructor;
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
public class OrdenCompraDTO extends BaseDTO {

    private String id;
    private ProveedorDTO proveedor;
    private LocalDate fechaOrden;
    private String estadoOrden;
    private BigDecimal subtotal;
    private BigDecimal IGV;
    private List<DetalleOrdenCompraDTO> detalles;
    private BigDecimal montoTotal;

    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private LocalDateTime fechaActualizacion = LocalDateTime.now();
}
