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
@Entity(name = "EmpleadoEntity")
@Table(name = "empleado")
public class EmpleadoEntity  extends BaseEntity {

    @Id
    @Column(name="id_cliente", nullable = false, unique = true,length = 20)
    private String id;

    @Column(name="nombres", nullable = false, length = 30)
    private String nombres;

    @Column(name="apellidos", nullable = false, length = 30)
    private String apellidos;

    @Column(name="direccion ", nullable = false, length = 50)
    private String direccion;

    @Column(name="email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name="contraseña", nullable = false, length = 100)
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private RolEntity rol;

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento_identidad")
    private TipoDocumentoIdentidadEntity tipoDocumento;

    @Enumerated(EnumType.STRING)
    private TipoSexo sexo;

    @Column(name="telefono", length = 9, unique = true)
    private String telefono;

    @Column(name="estado", nullable = false)
    private boolean estado = true;

}
