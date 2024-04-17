package com.store.dominguez.model;


import com.store.dominguez.model.base.BaseEntity;
import com.store.dominguez.model.enums.EstadoOrden;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SuperBuilder
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity(name = "OrdenCompraEntity")
@Table(name = "orden_compra")
public class OrdenCompraEntity extends BaseEntity {

    @Id
    @Column(name = "id_orden_compra", nullable = false, unique = true, length = 20)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private ProveedorEntity proveedor;

    @Column(name = "fecha_orden", nullable = false, columnDefinition = "DATE")
    private LocalDate fechaOrden;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_orden", nullable = false)
    private EstadoOrden estadoOrden;

    @Column(name = "subtotal", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private BigDecimal subtotal;

    @OneToMany(mappedBy = "ordenCompra", cascade = CascadeType.ALL)
    private List<DetalleOrdenCompraEntity> detalles;

    @Column(name="IGV", nullable = false)
    private BigDecimal IGV;

    @Column(name = "montototal", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private BigDecimal montototal;


    public void calcularTotales(BigDecimal subtotal) {
        // Inicializar variables
        BigDecimal igv = BigDecimal.ZERO;
        BigDecimal subTotal = BigDecimal.ZERO;

        // Calcular IGV
        igv = subtotal.multiply(BigDecimal.valueOf(0.18));

        // Calcular subtotal
        subtotal = subtotal.subtract(igv);

        // Calcular monto total
        subTotal = subtotal.add(igv);

        // Asignar valores
        this.subtotal = subtotal;
        this.IGV = igv;
        this.montototal = subTotal;
    }

    @Override
    public String toString() {
        return "OrdenCompraEntity{" +
                "id='" + id + '\'' +
                ", proveedor=" + proveedor +
                ", fechaOrden=" + fechaOrden +
                ", estadoOrden=" + estadoOrden +
                ", subtotal=" + subtotal +
                ", IGV=" + IGV +
                ", montototal=" + montototal +
                '}';
    }
}
