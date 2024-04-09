package com.store.dominguez.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private BigDecimal precioUnitario;

    @Column(name = "precio_total", nullable = false)
    private BigDecimal precioTotal;


    // Método para calcular el subtotal del detalle
    public void calcularPrecioTotal() {
        this.precioUnitario = this.productos.getPrecio();
        this.precioTotal = this.precioUnitario.multiply(BigDecimal.valueOf(this.cantidad));
    }

    @Override
    public String toString() {
        return "DocDetalleVentaEntity{" +
                "idDetalleVenta=" + idDetalleVenta +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", precioTotal=" + precioTotal +
                '}';
    }

}
