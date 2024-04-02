package com.store.dominguez.model;

import com.store.dominguez.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity(name = "TipoTransaccionEntity")
@Table(name = "tipo_transaccion")
public class TipoTransaccionEntity extends BaseEntity {

    @Id
    @Column(name = "id_tipo_transaccion", nullable = false, unique = true, length = 20)
    private String id;

    @Column(name = "nombre", unique = true, length = 50, nullable = false)
    private String nombre;

    @Column(name="estado")
    private Boolean estado = true;

    @OneToMany(mappedBy = "tipoTransaccion", cascade = CascadeType.ALL)
    private List<DocVentaEntity> ventas;


}
