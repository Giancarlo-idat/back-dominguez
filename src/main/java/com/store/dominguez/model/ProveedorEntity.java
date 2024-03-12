package com.store.dominguez.model;


import com.store.dominguez.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity(name = "ProveedorEntity")
@Table(name = "proveedores")
public class ProveedorEntity extends BaseEntity {

    @Id
    @Column(name="id_proveedor", nullable = false, unique = true,length = 50)
    private String id;

    @Column(name="nombres", nullable = false, length = 50)
    private String nombres;

    @Column(name="direccion", length = 50)
    private String direccion;

    @Column(name="email", nullable = false, length = 30)
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento_identidad")
    private TipoDocumentoIdentidadEntity tipoDocumento;

    @Column(name="numero_documento", nullable = false, length = 11)
    private String numeroDocumento;

    @Column(name="telefono", length = 9)
    private String telefono;

    @Column(name="estado")
    private boolean estado = true;


}
