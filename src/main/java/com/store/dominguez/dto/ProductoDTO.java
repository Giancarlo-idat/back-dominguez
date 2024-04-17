package com.store.dominguez.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.dominguez.dto.base.BaseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductoDTO extends BaseDTO {

    private String modelo;
    private String descripcion;
    private BigDecimal precio;
    private String marca;
    private int stock;
    private String imagen;
    @Builder.Default
    private boolean estado = true;
    private CategoriaDTO categoria;
    private JsonNode fichaTecnica;

    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

    public static ProductoDTO fromJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, ProductoDTO.class);
    }
}
