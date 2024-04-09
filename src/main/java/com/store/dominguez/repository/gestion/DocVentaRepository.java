package com.store.dominguez.repository.gestion;

import com.store.dominguez.dto.DocVentaDTO;
import com.store.dominguez.model.DocVentaEntity;
import com.store.dominguez.model.EstadoEnvio;
import com.store.dominguez.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DocVentaRepository extends BaseRepository<DocVentaEntity, String> {

    // Buscar DocVentas por Estado de Envio - "PENDIENTE", "EN_CAMINO", "ENTREGADO"
    @Query("SELECT docVenta FROM DocVentaEntity docVenta WHERE docVenta.estadoEnvio = :estadoEnvio")
    List<DocVentaEntity> buscarDocVentaPorEstadoEnvio(@Param("estadoEnvio") EstadoEnvio estadoEnvio);

    @Query("SELECT docVenta FROM DocVentaEntity docVenta WHERE docVenta.estado = true")
    List<DocVentaEntity> buscarDocVentaActivo();

    @Query("SELECT docVenta FROM DocVentaEntity docVenta WHERE docVenta.estado = false")
    List<DocVentaEntity> buscarDocVentaInactivo();


    @Query("SELECT docVenta FROM DocVentaEntity docVenta WHERE docVenta.numComprobante LIKE %:numComprobante%")
    List<DocVentaEntity> buscarDocVentaPorNumeroSeguimiento(@Param("numComprobante") String numComprobante);

    Optional<DocVentaEntity> findByIdVenta(String id);
    boolean existsByIdVentaAndClienteEmail(String idDocumento, String emailCliente);


}
