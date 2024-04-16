package com.store.dominguez.service.gestion;

import com.store.dominguez.dto.DocVentaDTO;
import com.store.dominguez.model.enums.EstadoEnvio;
import com.store.dominguez.service.base.BaseService;

import java.util.List;

public interface DocVentaService extends BaseService<DocVentaDTO> {

    List<DocVentaDTO> buscarDocVentaPorNumeroSeguimiento(String datos);
    List<DocVentaDTO> buscarDocVentaPorEstadoEnvio(EstadoEnvio estadoEnvio);
    boolean existsByIdAndClienteEmail(String idDocumento, String emailCliente);

}
