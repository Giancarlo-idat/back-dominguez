package com.store.dominguez.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.store.dominguez.dto.base.BaseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GuiaSalidaDTO extends BaseDTO {

    private String id;
    private LocalDate fechaSalida;
    private ClienteDTO cliente;
    private Set<DetalleGuiaSalidaDTO> detalles = new HashSet<>();
}
