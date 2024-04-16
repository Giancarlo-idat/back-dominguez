package com.store.dominguez.dto;


import com.store.dominguez.dto.base.BaseDTO;
import com.store.dominguez.model.enums.TipoSexo;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmpleadoDTO extends BaseDTO {

    private String id;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String email;
    private String password;
    private TipoDocumentoIdentidadDTO tipoDocumento;
    private String numeroDocumento;
    private TipoSexo sexo;
    private String telefono;
    @Builder.Default
    private boolean estado = true;
    private RolDTO rol;
}
