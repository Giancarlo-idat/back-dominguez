package com.store.dominguez.repository.gestion;

import com.store.dominguez.model.CategoriaEntity;
import com.store.dominguez.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepository extends BaseRepository<CategoriaEntity, String> {

    @Query("SELECT cat FROM CategoriaEntity cat WHERE cat.estado = true")
    List<CategoriaEntity> buscarCategoriaActivo();

    @Query("SELECT cat FROM CategoriaEntity cat WHERE cat.estado = false")
    List<CategoriaEntity> buscarCategoriaInactivo();

    @Query("SELECT cat FROM CategoriaEntity cat WHERE cat.nombre LIKE %:nombre%")
    List<CategoriaEntity> buscarCategoria(@Param("nombre") String nombre);

    boolean existsByNombre(String nombre);
}
