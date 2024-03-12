package com.store.dominguez.service.gestion;

import com.store.dominguez.dto.ProductoDTO;
import com.store.dominguez.service.base.BaseService;

import java.math.BigDecimal;
import java.util.List;

public interface ProductoService extends BaseService<ProductoDTO> {

    List<ProductoDTO> buscarPorMarca(String marca);
    List<ProductoDTO> buscarPorCategoria(String categoria);
    List<ProductoDTO> buscarPorRangoPrecio(BigDecimal precioMin, BigDecimal precioMax);
    List<ProductoDTO> buscarModelo(String modelo);

}
