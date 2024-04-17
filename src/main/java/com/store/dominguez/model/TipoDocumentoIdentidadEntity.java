package com.store.dominguez.model;

import com.store.dominguez.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@SuperBuilder
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "TipoDocumentoIdentidadEntity")
@Table(name = "tipo_documento_identidad")
public class TipoDocumentoIdentidadEntity extends BaseEntity {

    @Id
    @Column(name = "id_tipo_documento_identidad", nullable = false, unique = true)
    private String id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @OneToMany(mappedBy = "tipoDocumento")
    private Set<EmpleadoEntity> empleados;
}
