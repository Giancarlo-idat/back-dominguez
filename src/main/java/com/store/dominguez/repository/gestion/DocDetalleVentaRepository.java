package com.store.dominguez.repository.gestion;

import com.store.dominguez.model.DocDetalleVentaEntity;
import com.store.dominguez.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DocDetalleVentaRepository extends BaseRepository<DocDetalleVentaEntity, UUID> {

    // Buscar por el id del documento
    @Query("SELECT d FROM DocDetalleVentaEntity d WHERE d.venta.id = :ventaId")
    Optional<DocDetalleVentaEntity> findByVentaId(@Param("ventaId") String ventaId);

    // BUscar por el id del producto
    @Query("SELECT d FROM DocDetalleVentaEntity d WHERE d.productos.id = :productId")
    List<DocDetalleVentaEntity> findByProductId(@Param("productId") String productId);

    @Query("SELECT d FROM DocDetalleVentaEntity d WHERE d.precioTotal BETWEEN :minPrice AND :maxPrice")
    List<DocDetalleVentaEntity> findByPrecioTotalBetween(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);

}
