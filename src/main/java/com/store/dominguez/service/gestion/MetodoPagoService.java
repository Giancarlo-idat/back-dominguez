package com.store.dominguez.service.gestion;

import com.store.dominguez.dto.MetodoPagoDTO;
import com.store.dominguez.service.base.BaseService;

public interface MetodoPagoService extends BaseService<MetodoPagoDTO> {

    MetodoPagoDTO findMetodoPagoByNombre(String nombre);

}
