package com.store.dominguez.repository.gestion;

import com.store.dominguez.model.DetalleGuiaSalidaEntity;
import com.store.dominguez.repository.base.BaseRepository;

import java.util.List;

public interface DetalleGuiaSalidaRepository extends BaseRepository<DetalleGuiaSalidaEntity, String> {
    List<DetalleGuiaSalidaEntity> findByGuiaSalidaId(String guiaSalidaId);
    List<DetalleGuiaSalidaEntity> findByProductoId(String productoId);
}
