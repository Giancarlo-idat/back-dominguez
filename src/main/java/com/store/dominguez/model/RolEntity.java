package com.store.dominguez.model;

import com.store.dominguez.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Set;


@SuperBuilder
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity(name = "RolEntity")
@Table(name = "rol")
public class RolEntity extends BaseEntity {


    @Id
    @Column(name = "id_rol", nullable = false, unique = true, length = 50)
    private String id;

    @Column(name = "nombre", nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(name = "estado", nullable = false)
    private boolean estado = true;

    @Column(name = "descripcion", length = 100)
    private String descripcion;

    @OneToMany(mappedBy = "rol")
    private Set<EmpleadoEntity> empleados = new HashSet<>();

    @OneToMany(mappedBy = "rol")
    private Set<ClienteEntity> clientes = new HashSet<>();

    @OneToMany(mappedBy = "rol")
    private Set<RolPermisoEntity> permisos = new HashSet<>();

}
