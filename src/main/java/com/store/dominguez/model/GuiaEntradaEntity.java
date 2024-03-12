package com.store.dominguez.model;


import com.store.dominguez.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity(name = "GuiaEntradaEntity")
@Table(name = "guia_entrada")
public class GuiaEntradaEntity extends BaseEntity {

    @Id
    @Column(name="id_guia_entrada", nullable = false, unique = true,length = 20)
    private String id;

    @Column(name="fecha_entrada", nullable = false)
    private LocalDate   fechaEntrada;

    @ManyToOne
    @JoinColumn(name = "id_factura_compra")
    private FacturaCompraEntity facturaCompra;
}
