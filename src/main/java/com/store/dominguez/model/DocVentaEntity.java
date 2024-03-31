package com.store.dominguez.model;

import com.store.dominguez.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "DocVentaEntity")
@Table(name = "doc_venta")
public class DocVentaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idVenta;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteEntity idCliente;

    private String numComprobante;
    private LocalDate fechaEnvio;
    private LocalDate fechaEntrega;

    @ManyToOne
    @JoinColumn(name = "id_tipo_transaccion")
    private TipoTransaccionEntity idTipoTransaccion;


    private double impuesto;
    private BigDecimal precioUnitario;
    private BigDecimal precioTotal;
    private EstadoEnvio estadoEnvio;
    private boolean estado;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DocDetalleVentaEntity> detallesVenta;

    public void calcularImpuestoYPrecioTotal() {
        BigDecimal total = BigDecimal.ZERO;
        double impuestoTotal = 0.0;

        for (DocDetalleVentaEntity detalle : detallesVenta) {
            total = total.add(detalle.getPrecioTotal()); // Suma el precio total de cada detalle
        }

        this.precioTotal = total;
        this.impuesto = total.multiply(BigDecimal.valueOf(0.18)).doubleValue();
    }
}
