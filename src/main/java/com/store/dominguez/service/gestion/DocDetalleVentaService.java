package com.store.dominguez.service.gestion;

import com.store.dominguez.dto.DetalleDocVentaDTO;
import com.store.dominguez.service.base.BaseService;

import java.util.List;

public interface DocDetalleVentaService extends BaseService<DetalleDocVentaDTO> {

    List<DetalleDocVentaDTO> buscarDocDetalleVenta(String datos);
}
