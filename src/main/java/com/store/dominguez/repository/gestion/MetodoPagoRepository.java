package com.store.dominguez.repository.gestion;

import com.store.dominguez.model.MetodoPagoEntity;
import com.store.dominguez.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;

public interface MetodoPagoRepository extends BaseRepository<MetodoPagoEntity, String> {

    // Buscar por el nombre del método de pago
    @Query("SELECT m FROM MetodoPagoEntity m WHERE m.nombre = ?1")
    MetodoPagoEntity findMetodoPagoByNombre(String nombre);
    boolean existsMetodoPagoById(String idMetodoPago);
}
