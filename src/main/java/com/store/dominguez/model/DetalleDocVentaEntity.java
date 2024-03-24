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

    // Precio unitario del producto
    @Column(name = "precio_unitario")
    private BigDecimal precio_unitario;

    // Precio total de la venta
    @Column(name = "precio_total")
    private BigDecimal precio_total;

    // Referencia a la venta que le corresponde
    @ManyToOne
    @JoinColumn(name = "id_doc_venta")
    private DocVentaEntity docVenta;

    @PrePersist
    @PreUpdate
    public void validar() {
        if(cantidad <= 0  || precio_unitario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("La cantidad y el precio unitario deben ser mayores a 0");
        }
    }

}
