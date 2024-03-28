package com.store.dominguez.repository.gestion;

import com.store.dominguez.model.MetodoPagoEntity;
import com.store.dominguez.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MetodoPagoRepository extends BaseRepository<MetodoPagoEntity, String> {

    // Buscar por el nombre del método de pago
    @Query("SELECT m FROM MetodoPagoEntity m WHERE m.nombre LIKE %:nombre%")
    MetodoPagoEntity findMetodoPagoByNombre(@Param("nombre") String nombre);

    @Query("SELECT m FROM MetodoPagoEntity m WHERE m.estado = true")
    List<MetodoPagoEntity> buscarMetodoPagoActivo();

    @Query("SELECT m FROM MetodoPagoEntity m WHERE m.estado = false")
    List<MetodoPagoEntity> buscarMetodoPagoInactivo();

    boolean existsByNombre(String nombre);

    boolean existsMetodoPagoById(String idMetodoPago);
}
