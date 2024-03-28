package com.store.dominguez.service.gestion;

import com.store.dominguez.dto.DocDetalleVentaDTO;
import com.store.dominguez.service.base.BaseService;

import java.util.List;

public interface DocDetalleVentaService extends BaseService<DocDetalleVentaDTO> {

    List<DocDetalleVentaDTO> buscarDocDetalleVenta(String datos);

    List<DocDetalleVentaDTO> findByDocVentaId(String idDocVenta);

    List<DocDetalleVentaDTO> findByIdProduct(String idProducto);

}
