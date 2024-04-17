package com.store.dominguez.service.impl;

import com.store.dominguez.dto.ProductoDTO;
import com.store.dominguez.model.ProductoEntity;
import com.store.dominguez.repository.gestion.ProductoRepository;
import com.store.dominguez.service.gestion.ProductoService;
import com.store.dominguez.util.generator.IdGenerator;
import com.store.dominguez.util.validations.ProductoValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ModelMapper modelMapper;
    private final ProductoValidator productoValidator;

    public ProductoServiceImpl(ProductoRepository productoRepository, ProductoValidator productoValidator, ModelMapper modelMapper) {
        this.productoRepository = productoRepository;
        this.modelMapper = modelMapper;
        this.productoValidator = productoValidator;
    }

    @Override
    public List<ProductoDTO> buscarTodos() {
        List<ProductoEntity> listProductos = productoRepository.findAll();
        return listProductos.stream().
                map(producto -> modelMapper.map(producto, ProductoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> buscarActivo() {
        List<ProductoEntity> listProductos = productoRepository.buscarProductosActivo();
        return listProductos.stream().
                map(producto -> modelMapper.map(producto, ProductoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> buscarInactivo() {
        try {
            List<ProductoEntity> listProductos = productoRepository.buscarProductosInactivo();
            return listProductos.stream().
                    map(producto -> modelMapper.map(producto, ProductoDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el producto" + e.getMessage());
        }
    }

    @Override
    public Optional<ProductoDTO> buscarId(String id) {

        ProductoValidator.validarCamposBuscar(id);

        try {
            Optional<ProductoEntity> producto = productoRepository.findById(id);
            if (producto.isPresent()) {
                return Optional.of(modelMapper.map(producto.get(), ProductoDTO.class));
            } else {
                throw new RuntimeException("El producto no existe");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el producto" + e.getMessage());
        }
    }

    @Override
    public ProductoDTO agregar(ProductoDTO productoDTO) {

        productoValidator.validarCategoriaAsignada(productoDTO);
        productoValidator.validarProducto(productoDTO);

        try {
            String id = IdGenerator.generarID("PROD", productoDTO.getModelo());
            productoDTO.setId(id);
            ProductoEntity productoEntity = modelMapper.map(productoDTO, ProductoEntity.class);
            productoRepository.save(productoEntity);
            return modelMapper.map(productoEntity, ProductoDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al agregar el producto" + e.getMessage());
        }
    }


    @Override
    public ProductoDTO actualizar(ProductoDTO productoDTO, String id) {

        // Valida que el id no sea vacío
        ProductoValidator.validarId(id);

        try {
            Optional<ProductoEntity> productoEntity = productoRepository.findById(id);
            if (productoEntity.isPresent()) {
                productoValidator.validarProducto(productoDTO);
                ProductoEntity producto = productoEntity.get();
                modelMapper.map(productoDTO, producto);
                producto.setFechaActualizacion(LocalDateTime.now());
                return modelMapper.map(productoRepository.save(producto), ProductoDTO.class);
            } else {
                throw new RuntimeException("El producto no existe");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el producto" + e.getMessage());
        }

    }

    @Override
    public void eliminar(String id) {

        // Valida que el id no sea vacío
        ProductoValidator.validarId(id);
        try {
            Optional<ProductoEntity> productoEntity = productoRepository.findById(id);
            if (productoEntity.isPresent()) {
                ProductoEntity producto = productoEntity.get();
                producto.setEstado(false);
                producto.setFechaActualizacion(LocalDateTime.now());
                modelMapper.map(productoRepository.save(producto), ProductoDTO.class);
            } else {
                throw new RuntimeException("El producto no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el producto" + e.getMessage());
        }
    }

    @Override
    public ProductoDTO habilitar(String id) {

        // Valida que el id no sea vacío
        ProductoValidator.validarId(id);

        try {
            Optional<ProductoEntity> producto = productoRepository.findById(id);
            if (producto.isPresent()) {
                ProductoEntity productoEntity = producto.get();
                productoEntity.setEstado(true);
                productoEntity.setFechaActualizacion(LocalDateTime.now());
                return modelMapper.map(productoRepository.save(productoEntity), ProductoDTO.class);
            } else {
                throw new RuntimeException("El producto no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al habilitar el producto" + e.getMessage());
        }
    }

    @Override
    public List<ProductoDTO> buscarPorMarca(String marca) {

        // Valida que el campo a buscar no sea vacío
        ProductoValidator.validarCamposBuscar(marca);

        try {
            List<ProductoEntity> listProductos = productoRepository.buscarPorMarca(marca);
            return listProductos.stream().
                    map(producto -> modelMapper.map(producto, ProductoDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el producto" + e.getMessage());
        }

    }

    @Override
    public List<ProductoDTO> buscarPorCategoria(String categoria) {
        // Valida que el campo a buscar no sea vacío
        ProductoValidator.validarCamposBuscar(categoria);
        try {
            List<ProductoEntity> listProductos = productoRepository.buscarCategoria(categoria);
            return listProductos.stream().
                    map(producto -> modelMapper.map(producto, ProductoDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el producto" + e.getMessage());
        }
    }

    @Override
    public List<ProductoDTO> buscarPorRangoPrecio(BigDecimal precioMin, BigDecimal precioMax) {

        ProductoValidator.validarCamposPrecio(precioMin, precioMax);
        try {
            List<ProductoEntity> listProductos = productoRepository.buscarPorRangoPrecio(precioMin, precioMax);
            return listProductos.stream().
                    map(producto -> modelMapper.map(producto, ProductoDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el producto" + e.getMessage());
        }
    }

    @Override
    public List<ProductoDTO> buscarModelo(String modelo) {

        // Valida que el campo a buscar no sea vacío
        ProductoValidator.validarCamposBuscar(modelo);
        try {
            List<ProductoEntity> listProductos = productoRepository.buscarPorModelo(modelo);
            return listProductos.stream().
                    map(producto -> modelMapper.map(producto, ProductoDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el producto" + e.getMessage());
        }
    }
}