package com.store.dominguez.service.gestion;

import com.store.dominguez.dto.DocDetalleVentaDTO;
import com.store.dominguez.service.base.BaseService;

import java.util.List;
import java.util.UUID;

public interface DocDetalleVentaService extends BaseService<DocDetalleVentaDTO> {

    List<DocDetalleVentaDTO> buscarDocDetalleVenta(String datos);

    List<DocDetalleVentaDTO> findByDocVentaId(UUID idDocVenta);

    List<DocDetalleVentaDTO> findByDocVentaId(String idDocVenta);

    List<DocDetalleVentaDTO> findByIdProduct(String idProducto);

}
