package com.store.dominguez.dto;

import com.store.dominguez.dto.base.BaseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RolDTO extends BaseDTO {

    private String nombre;
    private String descripcion;
    @Builder.Default
    private boolean estado = true;

}
