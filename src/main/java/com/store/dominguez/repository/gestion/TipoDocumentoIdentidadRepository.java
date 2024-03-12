package com.store.dominguez.repository.gestion;

import com.store.dominguez.model.TipoDocumentoIdentidadEntity;
import com.store.dominguez.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TipoDocumentoIdentidadRepository extends BaseRepository<TipoDocumentoIdentidadEntity, String> {

    @Query("SELECT tdi FROM TipoDocumentoIdentidadEntity tdi WHERE tdi.estado = true")
    List<TipoDocumentoIdentidadEntity> buscarTipoDocumentoIdentidadActivo();

    @Query("SELECT tdi FROM TipoDocumentoIdentidadEntity tdi WHERE tdi.estado = false")
    List<TipoDocumentoIdentidadEntity> buscarTipoDocumentoIdentidadInactivo();

    @Query("SELECT tdi FROM TipoDocumentoIdentidadEntity tdi WHERE tdi.nombre LIKE %:documento%")
    List<TipoDocumentoIdentidadEntity> buscarTipoDocumentoIdentidad(@Param("documento") String documento);

    boolean existsByNombre(String nombre);

    boolean existsByEstado(boolean estado);

}
