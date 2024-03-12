package com.store.dominguez.service.gestion;

import com.store.dominguez.dto.TipoDocumentoIdentidadDTO;
import com.store.dominguez.service.base.BaseService;

import java.util.List;

public interface TipoDocumentoIdentidadService extends BaseService<TipoDocumentoIdentidadDTO>{

    List<TipoDocumentoIdentidadDTO> buscarTipoDocumentoIdentidad(String documento);
}
