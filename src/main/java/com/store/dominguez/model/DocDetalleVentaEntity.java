package com.store.dominguez.model;


import com.store.dominguez.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@SuperBuilder
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "DocDetalleVentaEntity")
@Table(name = "doc_detalle_venta")
public class DocDetalleVentaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idDetalleVenta;

    @ManyToOne
    @JoinColumn(name = "id_venta")
    private DocVentaEntity venta;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private ProductoEntity productos;

    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal precioTotal;



}
