package com.store.dominguez.dto;

import com.store.dominguez.dto.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TipoTransaccionDTO extends BaseDTO {

        private String id;
        private String nombre;
        @Builder.Default
        private Boolean estado = true;
}
