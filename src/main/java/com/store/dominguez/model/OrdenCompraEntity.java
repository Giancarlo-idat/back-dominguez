package com.store.dominguez.model;


import com.store.dominguez.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    @Column(name = "estado_orden", nullable = false, length = 20)
    private String estadoOrden;

    @ManyToOne
    @JoinColumn(name = "id_metodo_pago")
    private MetodoPagoEntity metodoPago;


    @Column(name = "subtotal", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private BigDecimal subtotal;


    @Column(name="IGV", nullable = false)
    private BigDecimal IGV;

    @Column(name = "montototal", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private BigDecimal montototal;
}
