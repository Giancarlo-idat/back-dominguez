package com.store.dominguez.util.validations;

import com.store.dominguez.dto.CategoriaDTO;
import com.store.dominguez.repository.gestion.CategoriaRepository;
import org.springframework.stereotype.Component;

@Component
public class CategoriaValidator {

    private final CategoriaRepository categoriaRepository;

    public CategoriaValidator(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public void validarCategoria(CategoriaDTO categoria) {
        validarNombreCategoria(categoria.getNombre());
        validarExistenciaCategoria(categoria.getNombre());
    }

    public static void validarNombreCategoria(String categoria) {
        if (Validations.isBlank(categoria)) {
            throw new Exceptions.ValidacionException("El nombre de la categoría no puede estar vacío");
        }
    }

    public static void validarIdCategoria(String categoriaID) {
        if (Validations.isBlank(categoriaID)) {
            throw new Exceptions.ValidacionException("El id de la categoría no puede estar vacío");
        }
    }

    public void validarExistenciaCategoria(String categoria) {
        if (categoriaRepository.existsByNombre(categoria))
            throw new RuntimeException("La categoria ya existe");

    }

}
