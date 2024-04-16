package com.store.dominguez.dto;

import com.store.dominguez.dto.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GuiaEntradaDTO extends BaseDTO {

    private String id;
    private LocalDate fechaEntrada;
    private FacturaCompraDTO facturaCompra;
}
