package com.store.dominguez.dto;


import com.store.dominguez.dto.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DetalleGuiaEntradaDTO extends BaseDTO {

    private GuiaEntradaDTO guiaEntrada;
    private ProductoDTO producto;
    private int cantidadDetalleEntrada;

}
