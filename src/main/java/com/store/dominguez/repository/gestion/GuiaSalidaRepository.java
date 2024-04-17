package com.store.dominguez.repository.gestion;

import com.store.dominguez.model.GuiaSalidaEntity;
import com.store.dominguez.repository.base.BaseRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface GuiaSalidaRepository extends BaseRepository<GuiaSalidaEntity, UUID> {

    List<GuiaSalidaEntity> findByClienteId(String clienteId);
    List<GuiaSalidaEntity> findByFechaSalidaBetween(LocalDate fechaInicio, LocalDate fechaFin);

}
