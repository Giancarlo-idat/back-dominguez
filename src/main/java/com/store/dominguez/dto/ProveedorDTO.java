package com.store.dominguez.dto;


import com.store.dominguez.dto.base.BaseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProveedorDTO extends BaseDTO {

    private String nombres;
    private String direccion;
    private String email;
    private TipoDocumentoIdentidadDTO tipoDocumento;
    private String numeroDocumento;
    private String telefono;
    @Builder.Default
    private boolean estado = true;

}
