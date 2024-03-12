package com.store.dominguez.repository.gestion;

import com.store.dominguez.model.ProductoEntity;
import com.store.dominguez.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductoRepository extends BaseRepository<ProductoEntity, String> {

    @Query("SELECT p FROM ProductoEntity p WHERE p.estado = true")
    List<ProductoEntity> buscarProductosActivo();

    @Query("SELECT p FROM ProductoEntity p WHERE p.estado = false")
    List<ProductoEntity> buscarProductosInactivo();

    @Query("SELECT p FROM ProductoEntity p WHERE p.marca LIKE %?1%")
    List<ProductoEntity> buscarPorMarca(String marca);

    @Query("SELECT p FROM ProductoEntity p JOIN p.categoria c WHERE c.nombre LIKE %:nombreCategoria%")
    List<ProductoEntity> buscarCategoria(@Param("nombreCategoria") String nombreCategoria);

    @Query("SELECT p FROM ProductoEntity p WHERE p.precio BETWEEN ?1 AND ?2")
    List<ProductoEntity> buscarPorRangoPrecio(BigDecimal precioMin, BigDecimal precioMax);

    @Query("SELECT p FROM ProductoEntity p WHERE p.modelo LIKE %?1%")
    List<ProductoEntity> buscarPorModelo(String modelo);

    boolean existsByModelo(String modelo);

    boolean existsByMarca(String marca);

}
