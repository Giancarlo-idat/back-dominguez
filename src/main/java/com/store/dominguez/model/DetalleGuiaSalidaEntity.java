package com.store.dominguez.model;

import com.store.dominguez.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "DetalleGuiaSalidaEntity")
@Table(name = "detalle_guia_salida")
public class DetalleGuiaSalidaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idDetalleGuiaSalida;

    private String numeroDetalle;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private ProductoEntity productos;

    @Column(name = "cantidad")
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "id_guia_salida")
    private GuiaSalidaEntity guiaSalida;


}
