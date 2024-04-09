package com.store.dominguez.service.impl;

import com.store.dominguez.dto.DocDetalleVentaDTO;
import com.store.dominguez.dto.DocVentaDTO;
import com.store.dominguez.dto.ProductoDTO;
import com.store.dominguez.model.DocDetalleVentaEntity;
import com.store.dominguez.model.DocVentaEntity;
import com.store.dominguez.model.ProductoEntity;
import com.store.dominguez.repository.gestion.DocDetalleVentaRepository;
import com.store.dominguez.service.gestion.DocDetalleVentaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DocDetalleVentaServiceImpl implements DocDetalleVentaService {

    private final DocDetalleVentaRepository docDetalleVentaRepository;
    private final ModelMapper modelMapper;

    public DocDetalleVentaServiceImpl(DocDetalleVentaRepository docDetalleVentaRepository, ModelMapper modelMapper) {
        this.docDetalleVentaRepository = docDetalleVentaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DocDetalleVentaDTO> buscarTodos() {
        try {
            List<DocDetalleVentaEntity> docDetalleVentaDTOS = docDetalleVentaRepository.findAll();
            return docDetalleVentaDTOS.stream()
                    .map(docDetalleVentaEntity -> {
                        DocDetalleVentaDTO docDetalleVentaDTO = new DocDetalleVentaDTO();
                        ProductoEntity productoEntity = docDetalleVentaEntity.getProductos();
                        ProductoDTO productoDTO = new ProductoDTO();
                        /*
                         *   Se asigna el valor de los atributos de la entidad a los atributos del DTO
                         * */
                        docDetalleVentaDTO.setIdDetalleVenta(docDetalleVentaEntity.getIdDetalleVenta());
                        docDetalleVentaDTO.setProductos(modelMapper.map(docDetalleVentaEntity.getProductos(), ProductoDTO.class));
                        docDetalleVentaDTO.setCantidad(docDetalleVentaEntity.getCantidad());
                        docDetalleVentaDTO.setPrecioUnitario(docDetalleVentaEntity.getPrecioUnitario());
                        docDetalleVentaDTO.setPrecioTotal(docDetalleVentaEntity.getPrecioTotal());
                        docDetalleVentaDTO.setFechaCreacion(docDetalleVentaEntity.getFechaCreacion());
                        docDetalleVentaDTO.setFechaActualizacion(docDetalleVentaEntity.getFechaActualizacion());

                        /*
                         *
                         * Establecer el ID  de la venta al DTO
                         * */
                        // Obtener la entidad DocVentaEntity
                        DocVentaEntity docVentaEntity = docDetalleVentaEntity.getVenta();

                        // Verificar si la entidad no es null antes de mapearla
                        if (docVentaEntity != null) {
                            // Crear un nuevo DTO para DocVentaEntity y asignarlo al campo idVenta
                            DocVentaDTO docVentaDTO = new DocVentaDTO();
                            docVentaDTO.setIdVenta((docVentaEntity.getIdVenta()));
                            docDetalleVentaDTO.setVenta(docVentaDTO);
                        }

                        return docDetalleVentaDTO;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al buscar los detalles de venta " + e.getMessage());
        }
    }

    @Override
    public List<DocDetalleVentaDTO> buscarActivo() {
        return null;
    }

    @Override
    public List<DocDetalleVentaDTO> buscarInactivo() {
        return null;
    }

    @Override
    public Optional<DocDetalleVentaDTO> buscarId(String id) {
        return Optional.empty();
    }

    @Override
    public DocDetalleVentaDTO agregar(DocDetalleVentaDTO docDetalleVentaDTO) {
        return null;
    }

    @Override
    public DocDetalleVentaDTO actualizar(DocDetalleVentaDTO docDetalleVentaDTO, String id) {
        return null;
    }

    @Override
    public void eliminar(String id) {

    }

    @Override
    public DocDetalleVentaDTO habilitar(String id) {
        return null;
    }

    @Override
    public List<DocDetalleVentaDTO> buscarDocDetalleVenta(String datos) {
        return null;
    }

    @Override
    public Optional<DocDetalleVentaDTO> findByDocVentaId(UUID idDocVenta) {
        try {
            Optional<DocDetalleVentaEntity> docDetalleVentaEntity = docDetalleVentaRepository.findByVentaId(idDocVenta);
            return docDetalleVentaEntity.map(docDetalleVentaEntity1 -> modelMapper
                    .map(docDetalleVentaEntity1, DocDetalleVentaDTO.class));

        } catch (Exception e) {
            throw new IllegalArgumentException("Error al buscar los detalles de venta");
        }
    }

    @Override
    public List<DocDetalleVentaDTO> findByIdProduct(String idProducto) {
        return null;
    }
}
