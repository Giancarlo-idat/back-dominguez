package com.store.dominguez.service.gestion;

import com.store.dominguez.dto.EmpleadoDTO;
import com.store.dominguez.service.base.BaseService;

import java.util.List;

public interface EmpleadoService extends BaseService<EmpleadoDTO> {

    List<EmpleadoDTO> buscarDatosEmpleado(String datos);
}
