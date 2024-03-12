package com.store.dominguez.util.validations;

import com.store.dominguez.dto.ProductoDTO;
import com.store.dominguez.model.CategoriaEntity;
import com.store.dominguez.repository.gestion.CategoriaRepository;
import com.store.dominguez.repository.gestion.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class ProductoValidator {

    @Autowired
    public ProductoRepository productoRepository;

    @Autowired
    public CategoriaRepository categoriaRepository;

    public void validarProducto(ProductoDTO producto) {
        validarPrecio(producto.getPrecio());
        validarStock(producto.getStock());
        validarCamposObligatorios(producto);
        validarPrecio(producto.getPrecio());
    }

    public void validarCategoriaAsignada(ProductoDTO productoDTO) {
        Optional<CategoriaEntity> categoria = categoriaRepository.findById(productoDTO.getCategoria().getId());
        if (categoria.isEmpty()) throw new IllegalArgumentException("La categoría no existe");
        if (categoria.get().getId().isBlank()) throw new IllegalArgumentException("La categoría no puede estar vacía");
        if (!categoria.get().isEstado()) throw new IllegalArgumentException("La categoría no está activada");
        if (!categoria.get().getNombre().trim().equalsIgnoreCase(productoDTO.getCategoria().getNombre().trim()))
            throw new IllegalArgumentException("La categoría no coincide");
    }

    public static void validarCamposBuscar(String producto) {
        if (Validations.isBlank(producto)) {
            throw new IllegalArgumentException("El campo a buscar no puede estar vacío");
        }
    }

    public static void validarCamposObligatorios(ProductoDTO producto) {
        if (Validations.isBlank(producto.getModelo()))
            throw new IllegalArgumentException("El modelo no puede ser vacío");
        if (Validations.isBlank(producto.getMarca()))
            throw new IllegalArgumentException("La marca no puede ser vacía");
    }

    public static void validarCamposPrecio(BigDecimal precioMin, BigDecimal precioMax) {
        if (precioMin == null || precioMax == null) {
            throw new IllegalArgumentException("Los campos de precio no pueden estar vacíos");
        }
        if (!Validations.isValidBigDecimal(precioMin) || !Validations.isValidBigDecimal(precioMax))
            throw new IllegalArgumentException("El precio no es válido");
    }

    public static void validarPrecio(BigDecimal precio) {
        if (!Validations.isValidBigDecimal(precio))
            throw new IllegalArgumentException("El precio no es válido");
    }

    public static void validarStock(int stock) {
        if (!Validations.isValidStock(stock))
            throw new IllegalArgumentException("El stock no es válido, debe ser mayor a 0");
        if (!Validations.isValidNumber(stock))
            throw new IllegalArgumentException("El stock no es válido, debe ser un número entero");
    }

    public static void validarId(String id) {
        if (Validations.isBlank(id)) {
            throw new IllegalArgumentException("El id no puede estar vacío");
        }
    }
}
