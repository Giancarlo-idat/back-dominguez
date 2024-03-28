package com.store.dominguez.service.gestion;


import com.store.dominguez.dto.TipoTransaccionDTO;
import com.store.dominguez.service.base.BaseService;

import java.util.List;

public interface TipoTransaccionService extends BaseService<TipoTransaccionDTO> {


    List<TipoTransaccionDTO> findByNombre(String nombre);
}
