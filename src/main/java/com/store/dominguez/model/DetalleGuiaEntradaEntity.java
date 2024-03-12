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
@Entity(name = "DetalleGuiaEntradaEntity")
@Table(name = "detalle_guia_entrada")
public class DetalleGuiaEntradaEntity extends BaseEntity {

    @Id
    @Column(name = "id_detalle_guia_entrada", nullable = false, unique = true, length = 20)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_guia_entrada")
    private GuiaEntradaEntity guiaEntrada;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private ProductoEntity producto;

    @Column(name = "cantidad_detalle_entrada")
    private int cantidadDetalleEntrada;

}
