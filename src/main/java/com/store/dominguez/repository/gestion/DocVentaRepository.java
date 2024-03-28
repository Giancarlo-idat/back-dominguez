package com.store.dominguez.repository.gestion;

import com.store.dominguez.model.DocVentaEntity;
import com.store.dominguez.model.EstadoEnvio;
import com.store.dominguez.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocVentaRepository extends BaseRepository<DocVentaEntity, String> {

    // Buscar DocVentas por Estado de Envio - "PENDIENTE", "EN_CAMINO", "ENTREGADO"
    @Query("SELECT docVenta FROM DocVentaEntity docVenta WHERE docVenta.estadoEnvio = :estadoEnvio")
    List<DocVentaEntity> buscarDocVentaPorEstadoEnvio(@Param("estadoEnvio") EstadoEnvio estadoEnvio);

    @Query("SELECT docVenta FROM DocVentaEntity docVenta WHERE docVenta.estado = true")
    List<DocVentaEntity> buscarDocVentaActivo();

    @Query("SELECT docVenta FROM DocVentaEntity docVenta WHERE docVenta.estado = false")
    List<DocVentaEntity> buscarDocVentaInactivo();


    @Query("SELECT docVenta FROM DocVentaEntity docVenta WHERE docVenta.numeroSeguimiento LIKE %:numeroSeguimiento%")
    List<DocVentaEntity> buscarDocVentaPorNumeroSeguimiento(@Param("numeroSeguimiento") String numeroSeguimiento);

    boolean existsClienteById(String idCliente);

    boolean existsMetodoPagoById(String idMetodoPago);
}
