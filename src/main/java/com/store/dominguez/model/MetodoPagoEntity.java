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
@Entity(name = "MetodoPagoEntity")
@Table(name = "metodo_pago")
public class MetodoPagoEntity extends BaseEntity {

    @Id
    @Column(name = "id_metodo_pago", nullable = false, unique = true, length = 20)
    private String id;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "estado", nullable = false)
    private boolean estado;

}