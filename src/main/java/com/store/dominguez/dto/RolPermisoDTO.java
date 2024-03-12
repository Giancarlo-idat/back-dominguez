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
public class RolPermisoDTO extends BaseDTO {

    private String nombre;
    private boolean estado = true;
    private RolDTO rol;
}
