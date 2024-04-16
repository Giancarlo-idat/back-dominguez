package com.store.dominguez.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleOrdenCompraDTO {
    private ProductoDTO producto;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal precioTotal;
}
