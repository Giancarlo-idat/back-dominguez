package com.store.dominguez.dto;


import com.store.dominguez.dto.base.BaseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DetalleGuiaSalidaDTO extends BaseDTO {
    private String id;
    private ProductoDTO producto;
    private int cantidad;
    private GuiaSalidaDTO guiaSalida;
}
