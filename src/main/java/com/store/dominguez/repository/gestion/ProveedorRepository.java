package com.store.dominguez.repository.gestion;


import com.store.dominguez.model.ProveedorEntity;
import com.store.dominguez.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProveedorRepository extends BaseRepository<ProveedorEntity, String>{

    @Query("SELECT pr FROM ProveedorEntity pr WHERE pr.estado = true")
    List<ProveedorEntity> buscarProveedorActivo();

    @Query("SELECT pr FROM ProveedorEntity pr WHERE pr.estado = false")
    List<ProveedorEntity> buscarProveedorInactivo();

    @Query("SELECT pr FROM ProveedorEntity pr WHERE pr.nombres LIKE %:datos% OR pr.direccion LIKE %:datos% OR pr.email LIKE %:datos%")
    List<ProveedorEntity> buscarDatosProveedor(@Param("datos") String datos);

    boolean existsByNumeroDocumento(String numeroDocumento);
}
