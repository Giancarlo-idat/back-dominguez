package com.store.dominguez.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.store.dominguez.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SuperBuilder
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity(name = "GuiaSalidaEntity")
@Table(name = "guia_salida")
public class GuiaSalidaEntity extends BaseEntity {
    @Id
    @Column(name = "id_guia_salida", nullable = false, unique = true)
    private String id;

    @Column(name = "fecha_salida")
    private LocalDate fechaSalida;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteEntity cliente;

    @JsonIgnore
    @OneToMany(mappedBy = "guiaSalida", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DetalleGuiaSalidaEntity> detalles = new HashSet<>();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        //result = prime * result + ((detalles == null) ? 0 : detalles.hashCode());
        return result;
    }
}
