package com.store.dominguez.model;

import com.store.dominguez.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@SuperBuilder
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "RolEntity")
@Table(name = "rol")
public class RolEntity extends BaseEntity {


    @Id
    @Column(name = "id_rol", nullable = false, unique = true, length = 50)
    private String id;

    @Column(name = "nombre", nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(name = "descripcion", length = 100)
    private String descripcion;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "rol")
    private List<EmpleadoEntity> empleados;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "rol")
    private List<ClienteEntity> clientes;


    @Override
    public String toString() {
        return "RolEntity{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

}
