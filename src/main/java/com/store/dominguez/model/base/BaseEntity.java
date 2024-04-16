package com.store.dominguez.model.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.store.dominguez.util.json.JsonLocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Super;

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
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @JsonSerialize(using = JsonLocalDateTimeSerializer.class)
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    public String getFormattedFechaCreacion() {
        return fechaCreacion.format(formatter);
    }

    public String getFormattedFechaActualizacion() {
        return fechaActualizacion.format(formatter);
    }


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
