package com.store.dominguez.model;


import com.store.dominguez.model.base.BaseEntity;
import com.store.dominguez.model.enums.EstadoFactura;
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
@Entity(name = "FacturaCompraEntity")
@Table(name = "factura_compra")
public class FacturaCompraEntity extends BaseEntity {

    @Id
    @Column(name = "id_factura_compra", nullable = false, unique = true, length = 20)
    private String id;

    @Column(name = "fecha_compra", nullable = false)
    private LocalDate fechaCompra;

    @ManyToOne
    @JoinColumn(name = "id_metodo_pago")
    private MetodoPagoEntity metodoPago;

    @Column(name = "subtotal", nullable = false)
    private BigDecimal subtotal;

    @Column(name = "IGV", nullable = false)
    private BigDecimal IGV;

    @Column(name = "monto_total", nullable = false)
    private BigDecimal montoTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoFactura estadoFactura;

    @ManyToOne
    @JoinColumn(name = "id_order_compra")
    private OrdenCompraEntity ordenCompra;

    @OneToMany(mappedBy = "facturaCompra")
    private List<GuiaEntradaEntity> guiasEntrada;
}
