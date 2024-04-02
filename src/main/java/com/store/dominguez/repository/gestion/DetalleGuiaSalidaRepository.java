package com.store.dominguez.repository.gestion;

import com.store.dominguez.model.DetalleGuiaSalidaEntity;
import com.store.dominguez.model.GuiaSalidaEntity;
import com.store.dominguez.model.ProductoEntity;
import com.store.dominguez.repository.base.BaseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DetalleGuiaSalidaRepository extends BaseRepository<DetalleGuiaSalidaEntity, String> {
    List<DetalleGuiaSalidaEntity> findByGuiaSalida(GuiaSalidaEntity guiaSalida);
    List<DetalleGuiaSalidaEntity> findByProductos(ProductoEntity productos);
    Optional<DetalleGuiaSalidaEntity> findByIdDetalleGuiaSalida(UUID id);
}
