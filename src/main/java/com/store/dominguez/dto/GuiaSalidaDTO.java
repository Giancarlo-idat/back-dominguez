package com.store.dominguez.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.store.dominguez.dto.base.BaseDTO;
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
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private LocalDateTime fechaActualizacion = LocalDateTime.now();
}
