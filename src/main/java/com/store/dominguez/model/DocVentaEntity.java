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
@Entity(name = "DocVentaEntity")
@Table(name = "doc_venta")
public class DocVentaEntity extends BaseEntity {

    @Id
    @Column(name = "id_doc_venta", nullable = false, unique = true, length = 20)
    private String id;

    @Column(name = "FechaPedido")
    private LocalDate fechaPedido;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteEntity cliente;

    @Column(name = "Tipodoc")
    private String tipoDoc;

    @ManyToOne
    @JoinColumn(name = "id_metodo_pago")
    private MetodoPagoEntity metodoPago;

    @Column(name = "Subtotal")
    private BigDecimal subtotal;

    @Column(name = "IGV")
    private BigDecimal IGV;

    @Column(name = "monto_total", columnDefinition = "DECIMAL(10,2)")
    private BigDecimal montoTotal;

    @Column(name = "estado")
    private Boolean estado;
}
