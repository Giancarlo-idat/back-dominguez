package com.store.dominguez.dto;


import com.store.dominguez.dto.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FacturaCompraDTO extends BaseDTO {

    private LocalDate fechaCompra;
    private MetodoPagoDTO metodoPago;
    private BigDecimal subtotal;
    private BigDecimal IGV;
    private BigDecimal montoTotal;
    private String estado;
    private OrdenCompraDTO ordenCompra;
}
