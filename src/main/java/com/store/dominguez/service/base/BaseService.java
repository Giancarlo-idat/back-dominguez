package com.store.dominguez.service.base;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    List<T> buscarTodos();
    List<T> buscarActivo();
    List<T> buscarInactivo();
    Optional<T> buscarId(String id);
    T agregar (T t);
    T actualizar (T t, String id);
    void eliminar (String id);
    T habilitar (String id);
}
