package com.store.dominguez.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = false)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DetalleGuiaSalidaDTO {
    private UUID idDetalleGuiaSalida;
    private String numeroDetalle;
    private ProductoDTO productos;
    private int cantidad;

    @Builder.Default
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @Builder.Default
    private LocalDateTime fechaActualizacion = LocalDateTime.now();
}
