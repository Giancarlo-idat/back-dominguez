package com.store.dominguez.service.gestion;

import com.store.dominguez.dto.GuiaSalidaDTO;
import com.store.dominguez.service.base.BaseService;

import java.time.LocalDate;
import java.util.List;

public interface GuiaSalidaService extends BaseService<GuiaSalidaDTO> {

    List<GuiaSalidaDTO> buscarPorCliente(Long clienteId);

    List<GuiaSalidaDTO> buscarPorFecha(LocalDate fechaInicio, LocalDate fechaFin);
}
