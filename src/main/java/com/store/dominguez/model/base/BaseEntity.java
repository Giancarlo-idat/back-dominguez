package com.store.dominguez.model.base;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.store.dominguez.util.json.JsonLocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @JsonSerialize(using = JsonLocalDateTimeSerializer.class)
    @Column(name = "fecha_creacion")
    @Builder.Default
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @JsonSerialize(using = JsonLocalDateTimeSerializer.class)
    @Column(name = "fecha_actualizacion")
    @Builder.Default
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    public String getFormattedFechaCreacion() {
        return fechaCreacion.format(formatter);
    }

    public String getFormattedFechaActualizacion() {
        return fechaActualizacion.format(formatter);
    }

    @Builder.Default
    @Column(name = "estado")
    private boolean estado = true;


    @PrePersist
    protected void prePersist() {
        if (this.fechaCreacion == null) {
            fechaCreacion = LocalDateTime.now();
        }
        if (this.fechaActualizacion == null) {
            fechaActualizacion = LocalDateTime.now();
        }
    }


    @PreUpdate
    protected void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    @PreRemove
    protected void preRemove() {
        this.fechaActualizacion = LocalDateTime.now();
    }

}
