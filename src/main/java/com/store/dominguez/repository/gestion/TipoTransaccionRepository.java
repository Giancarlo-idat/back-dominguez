package com.store.dominguez.repository.gestion;

import com.store.dominguez.model.TipoTransaccionEntity;
import com.store.dominguez.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TipoTransaccionRepository extends BaseRepository<TipoTransaccionEntity, String> {

    @Query("SELECT tipoTransaccion FROM TipoTransaccionEntity tipoTransaccion WHERE tipoTransaccion.estado = true")
    List<TipoTransaccionEntity> buscarTipoTransaccionActivo();

    @Query("SELECT tipoTransaccion FROM TipoTransaccionEntity tipoTransaccion WHERE tipoTransaccion.estado = false")
    List<TipoTransaccionEntity> buscarTipoTransaccionInactivo();

    @Query("SELECT tte FROM TipoTransaccionEntity tte WHERE tte.nombre LIKE %:nombre%")
    List<TipoTransaccionEntity> findByNombre(@Param("nombre") String nombre);

    boolean existsByNombre(String nombre);

}
