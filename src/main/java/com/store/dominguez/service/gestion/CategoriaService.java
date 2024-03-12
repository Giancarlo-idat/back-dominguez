package com.store.dominguez.service.gestion;

import com.store.dominguez.dto.CategoriaDTO;
import com.store.dominguez.service.base.BaseService;

import java.util.List;

public interface CategoriaService extends BaseService<CategoriaDTO> {

    List<CategoriaDTO> buscarCategoria(String datos);
}
