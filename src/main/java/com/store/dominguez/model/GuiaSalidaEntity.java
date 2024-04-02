package com.store.dominguez.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.store.dominguez.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.*;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idGuiSalida;

    @Column(name = "fecha_salida")
    private LocalDate fechaSalida;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteEntity cliente;

    private String numeroGuiaSalida;

    private String numeroSalida;

    @OneToMany(mappedBy = "guiaSalida", cascade = CascadeType.ALL)
    private List<DetalleGuiaSalidaEntity> detalles = new ArrayList<>();

}
