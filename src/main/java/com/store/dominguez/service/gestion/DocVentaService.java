package com.store.dominguez.service.gestion;

import com.store.dominguez.dto.DocVentaDTO;
import com.store.dominguez.model.EstadoEnvio;
import com.store.dominguez.repository.gestion.DocVentaRepository;
import com.store.dominguez.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DocVentaService extends BaseService<DocVentaDTO> {

    List<DocVentaDTO> buscarDocVentaPorNumeroSeguimiento(String datos);
    List<DocVentaDTO> buscarDocVentaPorEstadoEnvio(EstadoEnvio estadoEnvio);

    Optional<DocVentaDTO> buscarIdDocVenta(UUID id);
    boolean existsByIdAndClienteEmail(UUID idDocumento, String emailCliente);

}
