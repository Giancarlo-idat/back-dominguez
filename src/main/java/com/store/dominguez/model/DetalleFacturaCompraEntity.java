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
@Entity(name = "DetalleFacturaCompraEntity")
@Table(name = "detalle_factura_compra")
public class DetalleFacturaCompraEntity extends BaseEntity {

    @Id
    @Column(name = "id_detalle_factura_compra", nullable = false, unique = true, length = 50)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private ProductoEntity producto;

    @Column(name = "precio")
    private BigDecimal precio;

    @Column(name = "cantidad")
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "id_factura_compra")
    private FacturaCompraEntity facturaCompra;
}
