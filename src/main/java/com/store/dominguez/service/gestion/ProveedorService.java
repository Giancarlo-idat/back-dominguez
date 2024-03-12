package com.store.dominguez.service.gestion;

import com.store.dominguez.dto.ProveedorDTO;
import com.store.dominguez.service.base.BaseService;

import java.util.List;

public interface ProveedorService extends BaseService<ProveedorDTO> {

    List<ProveedorDTO> buscarDatosProveedor(String datos);
}
