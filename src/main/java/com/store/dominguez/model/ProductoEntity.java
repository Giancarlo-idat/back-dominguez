package com.store.dominguez.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.dominguez.model.base.BaseEntity;
import com.store.dominguez.util.json.JsonConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuperBuilder
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity(name = "ProductoEntity")
@Table(name = "producto")
public class ProductoEntity extends BaseEntity {

    @Id
    @Column(name = "id_producto", length = 50, nullable = false, unique = true)
    private String id;

    @Column(name = "modelo", length = 100, nullable = false)
    private String modelo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private BigDecimal precio;

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "estado", nullable = false)
    private boolean estado = true;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaEntity categoria;

    @OneToMany(mappedBy = "productos", cascade = CascadeType.ALL)
    private List<DocDetalleVentaEntity> detallesVenta = new ArrayList<>();

    @Convert(converter = JsonConverter.class)
    @Column(name = "ficha_tecnica", columnDefinition = "TEXT", nullable = false)
    private JsonNode fichaTecnica;


    @JsonIgnore
    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

    @JsonIgnore
    public static ProductoEntity fromJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, ProductoEntity.class);
    }
}
