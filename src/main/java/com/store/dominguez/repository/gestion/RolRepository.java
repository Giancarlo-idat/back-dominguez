package com.store.dominguez.repository.gestion;

import com.store.dominguez.model.RolEntity;
import com.store.dominguez.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RolRepository extends BaseRepository<RolEntity, String>{

    @Query("SELECT r FROM RolEntity r WHERE r.estado = true")
    List<RolEntity> buscarRolActivo();

    @Query("SELECT r FROM RolEntity r WHERE r.estado = false")
    List<RolEntity> buscarRolInactivo();

    @Query("SELECT r FROM RolEntity r WHERE r.nombre LIKE %:nombre%")
    List<RolEntity> buscarRol(String nombre);

    boolean existsByNombre(String nombre);
}
