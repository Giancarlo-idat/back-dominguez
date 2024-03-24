package com.store.dominguez.service.impl.gestion;

import com.store.dominguez.dto.DocVentaDTO;
import com.store.dominguez.model.DocVentaEntity;
import com.store.dominguez.model.EstadoEnvio;
import com.store.dominguez.repository.gestion.DocVentaRepository;
import com.store.dominguez.service.gestion.DocVentaService;
import com.store.dominguez.util.generator.NumeroSeguimientoGenerator;
import com.store.dominguez.util.validations.DocVentaValidator;
import com.store.dominguez.util.validations.Validations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocVentaServiceImpl implements DocVentaService {

    private final DocVentaRepository docVentaRepository;
    private final ModelMapper modelMapper;
    private final DocVentaValidator docVentaValidator;
    private final NumeroSeguimientoGenerator numeroSeguimientoGenerator;

    @Autowired
    public DocVentaServiceImpl(DocVentaRepository docVentaRepository, ModelMapper modelMapper, DocVentaValidator docVentaValidator, NumeroSeguimientoGenerator numeroSeguimientoGenerator) {
        this.docVentaRepository = docVentaRepository;
        this.modelMapper = modelMapper;
        this.docVentaValidator = docVentaValidator;
        this.numeroSeguimientoGenerator = numeroSeguimientoGenerator;
    }

    @Override
    public List<DocVentaDTO> buscarTodos() {
        try {
            List<DocVentaEntity> list = docVentaRepository.findAll();
            return list.stream().map(docVenta -> modelMapper.map(docVenta, DocVentaDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }
    }

    @Override
    public List<DocVentaDTO> buscarActivo() {
        return null;
    }

    @Override
    public List<DocVentaDTO> buscarInactivo() {
        return null;
    }

    @Override
    public Optional<DocVentaDTO> buscarId(String id) {

        if (Validations.isBlank(id)) throw new NullPointerException("El id no puede ser nulo o vacio");

        try {
            Optional<DocVentaEntity> docVentaEntity = docVentaRepository.findById(id);
            if (docVentaEntity.isPresent()) {
                return docVentaEntity.map(docVenta -> modelMapper.map(docVenta, DocVentaDTO.class));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }

    }

    @Override
    public DocVentaDTO agregar(DocVentaDTO docVentaDTO) {

        docVentaValidator.validarDocVenta(docVentaDTO);
        try {
            DocVentaEntity docVentaEntity = modelMapper.map(docVentaDTO, DocVentaEntity.class);
            docVentaEntity.setNumeroSeguimiento(numeroSeguimientoGenerator.generarNumeroSeguimiento());
            docVentaEntity = docVentaRepository.save(docVentaEntity);
            return modelMapper.map(docVentaEntity, DocVentaDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }
    }

    @Override
    public DocVentaDTO actualizar(DocVentaDTO docVentaDTO, String id) {

        if (Validations.isBlank(id)) throw new NullPointerException("El id no puede ser nulo o vacio");

        docVentaValidator.validarDocVenta(docVentaDTO);
        try {
            Optional<DocVentaEntity> docVentaEntity = docVentaRepository.findById(id);
            if (docVentaEntity.isPresent()) {
                DocVentaEntity docVenta = docVentaEntity.get();
                docVenta = modelMapper.map(docVentaDTO, DocVentaEntity.class);
                docVenta = docVentaRepository.save(docVenta);
                return modelMapper.map(docVenta, DocVentaDTO.class);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }
    }

    @Override
    public void eliminar(String id) {
    }

    @Override
    public DocVentaDTO habilitar(String id) {
        return null;
    }

    @Override
    public List<DocVentaDTO> buscarDocVentaPorNumeroSeguimiento(String datos) {

        if (Validations.isBlank(datos)) throw new NullPointerException("El dato no puede ser nulo o vacio");

        try {
            List<DocVentaEntity> list = docVentaRepository.buscarDocVentaPorNumeroSeguimiento(datos);
            return list.stream().map(docVenta -> modelMapper.map(docVenta, DocVentaDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }
    }

    @Override
    public List<DocVentaDTO> buscarDocVentaPorEstadoEnvio(EstadoEnvio estadoEnvio) {

        if (Validations.isBlank(estadoEnvio.name()))
            throw new NullPointerException("El dato no puede ser nulo o vacio");

        try {
            List<DocVentaEntity> list = docVentaRepository.buscarDocVentaPorEstadoEnvio(estadoEnvio);
            return list.stream().map(docVenta -> modelMapper.map(docVenta, DocVentaDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }
    }
}
