package com.store.dominguez.model;

import com.store.dominguez.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;


@SuperBuilder
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity(name = "RolPermisoEntity")
@Table(name = "rol_permiso")
public class RolPermisoEntity extends BaseEntity {

    @Id
    @Column(name="id_rol_permiso", nullable = false, unique = true,length = 50)
    private String id;

    @Column(name="nombre_permiso", nullable = false,unique = true, length = 50)
    private String nombre;

    @Column(name="estado", nullable = false)
    private boolean estado = true;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private RolEntity rol;

}
