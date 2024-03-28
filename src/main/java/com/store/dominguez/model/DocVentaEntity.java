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
    private ClienteEntity idCliente;

    @ManyToOne
    @JoinColumn(name = "id_metodo_pago")
    private MetodoPagoEntity metodoPago;

    // BOLETAS
    @ManyToOne
    @JoinColumn(name = "id_tipo_transaccion")
    private TipoTransaccionEntity tipoTransaccion;

    @Enumerated(EnumType.STRING)
    private EstadoEnvio estadoEnvio;

    @Column(name = "numero_seguimiento")
    private String numeroSeguimiento;

    @Column(name = "direccion_envio")
    private String direccionEnvio;

    @Column(name = "fecha_envio")
    private LocalDate fechaEnvio;

    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;

    @Column(name = "observaciones", length = 250)
    private String observaciones;

    @Column(name = "precio_total")
    private BigDecimal precio_total;

    @Column(name = "estado")
    private boolean estado = true;

    @OneToMany(mappedBy = "docVenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DocDetalleVentaEntity> detalleVenta = new HashSet<>();

    public void calcularPrecioTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (DocDetalleVentaEntity detalle : detalleVenta) {
            total = total.add(detalle.getPrecio_total());
        }
        this.precio_total = total;
    }

    public void agregarDetalleVenta(DocDetalleVentaEntity detalleVenta) {
        this.detalleVenta.add(detalleVenta);
        detalleVenta.setDocVenta(this);
        calcularPrecioTotal();
    }

    public void actualizarDetalleVenta(DocDetalleVentaEntity detalleVenta) {
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
