package com.store.dominguez.model;

import com.store.dominguez.model.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity(name = "CategoriaEntity")
@Table(name = "categoria")
public class CategoriaEntity  extends BaseEntity {

    @Id
    @Column(name="id_categoria", nullable = false, unique = true,length = 50)
    private String id;

    @Column(name="nombre", nullable = false,unique = true, length = 100)
    private String nombre;

    @Column(name="descripcion", length = 150)
    private String descripcion;

    @Column(name="estado", nullable = false)
    private boolean estado = true;

    @Column(name="imagen")
    private String imagen;

}
