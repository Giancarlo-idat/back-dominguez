package com.store.dominguez.model;


import com.store.dominguez.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SuperBuilder
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity(name = "DocVentaEntity")
@Table(name = "doc_venta")
public class DocVentaEntity extends BaseEntity {

    @Id
    @Column(name = "id_doc_venta", nullable = false, unique = true, length = 20)
    private String id;

    @Column(name = "fechaVenta")
    private LocalDate fechaVenta;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "id_metodo_pago")
    private MetodoPagoEntity metodoPago;

    // BOLETA O FACTURA O NOTA DE CRÉDITO
    @ManyToOne
    @JoinColumn(name = "id_tipo_transaccion")
    private TipoTransaccion tipoTransaccion;

    @Enumerated(EnumType.STRING)
    private EstadoEnvio estadoEnvio;

    private String numeroSeguimiento;

    @Column(name = "precio_total")
    private BigDecimal precio_total;

    @OneToMany(mappedBy = "docVenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DetalleDocVentaEntity> detalleVenta = new HashSet<>();

    public void calcularPrecioTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (DetalleDocVentaEntity detalle : detalleVenta) {
            total = total.add(detalle.getPrecio_total());
        }
        this.precio_total = total;
    }

    public void agregarDetalleVenta(DetalleDocVentaEntity detalleVenta) {
        this.detalleVenta.add(detalleVenta);
        detalleVenta.setDocVenta(this);
        calcularPrecioTotal();
    }

    public void actualizarDetalleVenta(DetalleDocVentaEntity detalleVenta) {
        this.detalleVenta.stream()
                .filter(detalle -> detalle.getId().equals(detalleVenta.getId()))
                .findFirst()
                .ifPresent(detalle -> {
                    detalle.setCantidad(detalleVenta.getCantidad());
                    detalle.setPrecio_unitario(detalleVenta.getPrecio_unitario());
                    detalle.setPrecio_total(detalleVenta.getPrecio_total());
                });
        calcularPrecioTotal();
    }

    public void eliminarDetalleVenta(String idDetalleVenta) {
        this.detalleVenta.removeIf(detalle -> detalle.getId().equals(idDetalleVenta));
        calcularPrecioTotal();
    }
}
