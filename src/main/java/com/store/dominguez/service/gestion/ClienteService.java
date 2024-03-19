package com.store.dominguez.service.gestion;

import com.store.dominguez.dto.ClienteDTO;
import com.store.dominguez.service.base.BaseService;

import java.util.List;

public interface ClienteService extends BaseService<ClienteDTO> {

    List<ClienteDTO> buscarDatosCliente(String datos);
}
