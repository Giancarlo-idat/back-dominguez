package com.store.dominguez.model;

import com.store.dominguez.model.base.BaseEntity;
import com.store.dominguez.model.enums.EstadoEnvio;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "DocVentaEntity")
@Table(name = "doc_venta")
public class DocVentaEntity extends BaseEntity {

    @Id
    @Column(name = "id_venta", length = 50, nullable = false, unique = true)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteEntity cliente;

    @Column(name = "numero_comprobante", length = 50, nullable = false)
    private String numComprobante;

    @Column(name = "fecha_envio", nullable = false)
    private LocalDate fechaEnvio;

    @Column(name = "fecha_entrega", nullable = false)
    private LocalDate fechaEntrega;

    @ManyToOne
    @JoinColumn(name = "id_tipo_transaccion")
    private TipoTransaccionEntity tipoTransaccion;

    @Column(name = "precio_total")
    private BigDecimal precioTotal;

    @Column(name = "op_gravadas")
    private BigDecimal opGravadas;

    @Column(name = "igv")
    private BigDecimal igv;

    private EstadoEnvio estadoEnvio;

    @OneToMany(mappedBy = "venta")
    private List<DocDetalleVentaEntity> detallesVenta;

    public void calcularTotales() {
        // Inicializar variables
        BigDecimal totalOpGravadas = BigDecimal.ZERO;

        // Calcular la suma de los precios totales de los detalles de venta
        for (DocDetalleVentaEntity detalle : detallesVenta) {
            totalOpGravadas = totalOpGravadas.add(detalle.getPrecioTotal());
        }

        // Calcular el impuesto (18% del total gravado)
        this.igv = totalOpGravadas.multiply(BigDecimal.valueOf(0.18));

        // Calcular la OP. GRAVADAS (Precio total de los detalles de venta)
        this.opGravadas = totalOpGravadas;

        // Calcular el precio total (suma de OP. GRAVADAS e IGV)
        this.precioTotal = this.opGravadas.add(this.igv);
    }

    @Override
    public String toString() {
        return "DocVentaEntity{" +
                "idVenta=" + id +
                ", numComprobante='" + numComprobante + '\'' +
                ", fechaEnvio=" + fechaEnvio +
                ", fechaEntrega=" + fechaEntrega +
                ", impuesto=" + igv +
                ", precioTotal=" + precioTotal +
                ", estadoEnvio=" + estadoEnvio +
                '}';
    }

}
