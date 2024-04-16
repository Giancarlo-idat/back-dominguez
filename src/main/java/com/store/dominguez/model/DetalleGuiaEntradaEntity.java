package com.store.dominguez.model;


import com.store.dominguez.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity(name = "DetalleGuiaEntradaEntity")
@Table(name = "detalle_guia_entrada")
public class DetalleGuiaEntradaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_guia_entrada")
    private GuiaEntradaEntity guiaEntrada;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private ProductoEntity producto;

}
