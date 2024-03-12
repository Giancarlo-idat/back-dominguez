package com.store.dominguez.dto;

import com.store.dominguez.dto.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DetalleDocVentaDTO extends BaseDTO {

    private ProductoDTO producto;
    private int cantidad;
    private BigDecimal precio;
    private DocVentaDTO docVenta;
}
