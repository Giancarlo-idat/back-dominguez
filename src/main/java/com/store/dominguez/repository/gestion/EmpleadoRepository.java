package com.store.dominguez.repository.gestion;

import com.store.dominguez.dto.EmpleadoDTO;
import com.store.dominguez.model.EmpleadoEntity;
import com.store.dominguez.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository extends BaseRepository<EmpleadoEntity, String> {


    @Query("SELECT e FROM EmpleadoEntity e WHERE e.estado = true")
    List<EmpleadoEntity> buscarEmpleadoActivo();

    @Query("SELECT e FROM EmpleadoEntity e WHERE e.estado = false")
    List<EmpleadoEntity> buscarEmpleadoInactivo();

    @Query("SELECT e FROM EmpleadoEntity e WHERE e.nombres LIKE %:datos% OR e.apellidos LIKE %:datos% OR e.email LIKE %:datos%")
    List<EmpleadoEntity> buscarDatosEmpleado(String datos);

    Optional<EmpleadoEntity> findByEmail(String email);

}
