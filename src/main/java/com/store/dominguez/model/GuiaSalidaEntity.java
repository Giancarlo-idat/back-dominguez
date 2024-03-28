package com.store.dominguez.model;


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
public class GuiaSalidaEntity {

    @Id
    @Column(name = "id_guia_salida", nullable = false, unique = true)
    private String id;

    @Column(name = "fecha_salida")
    private LocalDate fechaSalida;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteEntity cliente;

    @OneToMany(mappedBy = "guiaSalida", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DetalleGuiaSalidaEntity> detalles = new HashSet<>();
}
