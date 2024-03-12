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
public class OrdenCompraDTO extends BaseDTO {

    private ProveedorDTO proveedor;
    private String fechaOrden;
    private String estadoOrden;
    private MetodoPagoDTO metodoPago;
    private BigDecimal subtotal;
    private BigDecimal IGV;
    private BigDecimal montoTotal;
}
