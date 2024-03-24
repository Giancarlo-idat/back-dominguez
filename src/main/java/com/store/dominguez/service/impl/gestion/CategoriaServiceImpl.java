package com.store.dominguez.service.impl.gestion;

import com.store.dominguez.dto.CategoriaDTO;
import com.store.dominguez.model.CategoriaEntity;
import com.store.dominguez.repository.gestion.CategoriaRepository;
import com.store.dominguez.service.gestion.CategoriaService;
import com.store.dominguez.util.generator.IdGenerator;
import com.store.dominguez.util.validations.CategoriaValidator;
import com.store.dominguez.util.validations.Validations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ModelMapper modelMapper;

    private final CategoriaValidator categoriaValidator;

    @Autowired
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, ModelMapper modelMapper, CategoriaValidator categoriaValidator) {
        this.categoriaRepository = categoriaRepository;
        this.modelMapper = modelMapper;
        this.categoriaValidator = categoriaValidator;
    }

    @Override
    public List<CategoriaDTO> buscarTodos() {
        try {
            List<CategoriaEntity> list = categoriaRepository.findAll();
            return list.stream().map(cat -> modelMapper.map(cat, CategoriaDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }
    }

    @Override
    public List<CategoriaDTO> buscarActivo() {
        try {
            List<CategoriaEntity> list = categoriaRepository.buscarCategoriaActivo();
            return list.stream().map(cat -> modelMapper.map(cat, CategoriaDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }
    }

    @Override
    public List<CategoriaDTO> buscarInactivo() {
        try {
            List<CategoriaEntity> list = categoriaRepository.buscarCategoriaInactivo();
            return list.stream().map(cat -> modelMapper.map(cat, CategoriaDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }
    }

    @Override
    public Optional<CategoriaDTO> buscarId(String id) {

        if (Validations.isBlank(id)) throw new NullPointerException("El id no puede ser vacío");

        try {
            Optional<CategoriaEntity> categoria = categoriaRepository.findById(id);
            if (categoria.isPresent()) {
                return Optional.of(modelMapper.map(categoria.get(), CategoriaDTO.class));
            } else {
                throw new RuntimeException("La categoria no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }
    }

    @Override
    public CategoriaDTO agregar(CategoriaDTO categoriaDTO) {

        categoriaValidator.validarCategoria(categoriaDTO);

        try {
            String id = IdGenerator.generarID("CAT", categoriaDTO.getNombre());
            categoriaDTO.setId(id);
            CategoriaEntity categoriaEntity = modelMapper.map(categoriaDTO, CategoriaEntity.class);
            categoriaEntity = categoriaRepository.save(categoriaEntity);
            return modelMapper.map(categoriaEntity, CategoriaDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al agregar la categoria" + e.getMessage());
        }
    }

    @Override
    public CategoriaDTO actualizar(CategoriaDTO categoriaDTO, String id) {

        // Valida que el campo ID no esté vacío
        CategoriaValidator.validarIdCategoria(id);
        try {
            Optional<CategoriaEntity> categoriaEntity = categoriaRepository.findById(id);
            if (categoriaEntity.isPresent()) {
                // Valida que el campo nombre no esté vacío
                CategoriaValidator.validarNombreCategoria(categoriaDTO.getNombre());
                CategoriaEntity categoria = categoriaEntity.get();
                modelMapper.map(categoriaDTO, categoria);
                categoria.setFechaActualizacion(LocalDateTime.now());
                return modelMapper.map(categoriaRepository.save(categoria), CategoriaDTO.class);
            } else {
                throw new RuntimeException("La categoria no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la categoria" + e.getMessage());
        }


    }

    @Override
    public void eliminar(String id) {

        // Valida que el campo ID no esté vacío
        CategoriaValidator.validarIdCategoria(id);

        try {
            Optional<CategoriaEntity> categoriaEntity = categoriaRepository.findById(id);
            if (categoriaEntity.isPresent()) {
                CategoriaEntity categoria = categoriaEntity.get();
                categoria.setEstado(false);
                categoria.setFechaActualizacion(LocalDateTime.now());
                modelMapper.map(categoriaRepository.save(categoria), CategoriaDTO.class);
            } else {
                throw new RuntimeException("La categoria no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la categoria" + e.getMessage());
        }
    }

    @Override
    public CategoriaDTO habilitar(String id) {

        // Valida que el campo ID no esté vacío
        CategoriaValidator.validarIdCategoria(id);

        try {
            Optional<CategoriaEntity> categoriaEntity = categoriaRepository.findById(id);
            if (categoriaEntity.isPresent()) {
                CategoriaEntity categoria = categoriaEntity.get();
                categoria.setEstado(true);
                categoria.setFechaActualizacion(LocalDateTime.now());
                return modelMapper.map(categoriaRepository.save(categoria), CategoriaDTO.class);
            } else {
                throw new RuntimeException("La categoria no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al habilitar la categoria" + e.getMessage());
        }
    }

    @Override
    public List<CategoriaDTO> buscarCategoria(String datos) {

        // Valida que el campo nombre no esté vacío
        CategoriaValidator.validarNombreCategoria(datos);

        try {
            List<CategoriaEntity> list = categoriaRepository.buscarCategoria(datos);
            return list.stream().
                    map(cat -> modelMapper.map(cat, CategoriaDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }
    }
}
