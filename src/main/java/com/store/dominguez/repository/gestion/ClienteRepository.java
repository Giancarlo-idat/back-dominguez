package com.store.dominguez.repository.gestion;

import com.store.dominguez.model.ClienteEntity;
import com.store.dominguez.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends BaseRepository<ClienteEntity, String> {

    @Query("SELECT cl FROM ClienteEntity cl WHERE cl.estado = true")
    List<ClienteEntity> buscarClienteActivo();

    @Query("SELECT cl FROM ClienteEntity cl WHERE cl.estado = false")
    List<ClienteEntity> buscarClienteInactivo();

    @Query("SELECT cl FROM ClienteEntity cl WHERE cl.nombres LIKE %:datos% OR cl.apellidos LIKE %:datos% OR cl.email LIKE %:datos%")
    List<ClienteEntity> buscarDatosCliente(@Param("datos") String datos);

    Optional<ClienteEntity> findByEmail(String email);

    boolean existsByEmail(String email);

}
