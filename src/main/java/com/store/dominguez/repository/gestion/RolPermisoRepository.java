package com.store.dominguez.repository.gestion;

import com.store.dominguez.model.RolPermisoEntity;
import com.store.dominguez.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RolPermisoRepository extends BaseRepository<RolPermisoEntity, String>  {

    @Query("SELECT rp FROM RolPermisoEntity rp WHERE rp.estado = true")
    List<RolPermisoEntity> buscarRolPermisoActivo();

    @Query("SELECT rp FROM RolPermisoEntity rp WHERE rp.estado = false")
    List<RolPermisoEntity> buscarRolPermisoInactivo();

    @Query("SELECT rp FROM RolPermisoEntity rp WHERE rp.nombre LIKE %:datos%")
    List<RolPermisoEntity> buscarDatosRolPermiso(String datos);


}
