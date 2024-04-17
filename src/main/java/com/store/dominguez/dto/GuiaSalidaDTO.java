package com.store.dominguez.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GuiaSalidaDTO {

    private UUID idGuiSalida;
    private LocalDate fechaSalida;
    private ClienteDTO cliente;
    private String numeroGuiaSalida;
    private String numeroSalida;
    private List<DetalleGuiaSalidaDTO> detalles;
    @Builder.Default
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @Builder.Default
    private LocalDateTime fechaActualizacion = LocalDateTime.now();
}
