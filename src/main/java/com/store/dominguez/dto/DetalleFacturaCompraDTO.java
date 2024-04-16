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
public class DetalleFacturaCompraDTO extends BaseDTO {

    private String id;
    private ProveedorDTO proveedor;
    private BigDecimal precio;
    private int cantidad;
    private FacturaCompraDTO facturaCompra;
}
