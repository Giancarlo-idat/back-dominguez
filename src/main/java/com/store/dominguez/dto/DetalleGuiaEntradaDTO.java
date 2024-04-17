package com.store.dominguez.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DetalleGuiaEntradaDTO  {

    private UUID id;
    private GuiaEntradaDTO guiaEntrada;
    private ProductoDTO producto;
    private int cantidadDetalleEntrada;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;

}
