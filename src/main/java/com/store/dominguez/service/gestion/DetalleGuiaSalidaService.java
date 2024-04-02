package com.store.dominguez.service.gestion;

import com.store.dominguez.dto.DetalleGuiaSalidaDTO;
import com.store.dominguez.service.base.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DetalleGuiaSalidaService extends BaseService<DetalleGuiaSalidaDTO> {
    List<DetalleGuiaSalidaDTO> buscarPorGuiaSalida(String guiaSalidaId);

    List<DetalleGuiaSalidaDTO> buscarPorProducto(String productoId);

    Optional<DetalleGuiaSalidaDTO> buscarIdDetalleGuiaSalida(UUID id);
}
