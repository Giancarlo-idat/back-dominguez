package com.store.dominguez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.store.dominguez.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity(name = "DetalleGuiaSalidaEntity")
@Table(name = "detalle_guia_salida")
public class DetalleGuiaSalidaEntity extends BaseEntity {

    @Id
    @Column(name = "id_detalle_guia_salida", nullable = false, unique = true, length = 50)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private ProductoEntity producto;

    @Column(name = "cantidad")
    private int cantidad;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_guia_salida")
    private GuiaSalidaEntity guiaSalida;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        // Excluye la referencia a la guía de salida:
        //result = prime * result + ((guiaSalida == null) ? 0 : guiaSalida.hashCode());
        return result;
    }
}
