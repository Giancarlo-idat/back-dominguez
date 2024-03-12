package com.store.dominguez.service.gestion;

import com.store.dominguez.dto.RolDTO;
import com.store.dominguez.service.base.BaseService;

import java.util.List;

public interface RolService extends BaseService<RolDTO> {

    List<RolDTO> buscarRoles(String roles);
}
