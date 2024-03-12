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
@Entity(name = "CarritoComprasEntity")
@Table(name = "carrito_compras")
public class CarritoComprasEntity extends BaseEntity {

    @Id
    @Column(name="id_carrito_compra", nullable = false, unique = true,length = 50)
    private String id;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL)
    private List<ProductoEntity> producto;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClienteEntity cliente;

    @Column(name="cantidad", nullable = false)
    private int cantidad;

}
