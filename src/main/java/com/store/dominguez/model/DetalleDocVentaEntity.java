package com.store.dominguez.model;


import com.store.dominguez.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity(name = "DetalleDocVentaEntity")
@Table(name = "detalle_doc_venta")
public class DetalleDocVentaEntity extends BaseEntity {

    @Id
    @Column(name = "id_detalle_doc_venta", nullable = false, unique = true, length = 50)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private ProductoEntity producto;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "precio")
    private BigDecimal precio;

    @ManyToOne
    @JoinColumn(name = "id_doc_venta")
    private DocVentaEntity docVenta;
}
