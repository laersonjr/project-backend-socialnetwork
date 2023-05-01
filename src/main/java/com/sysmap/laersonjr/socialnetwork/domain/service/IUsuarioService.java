package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.UsuarioRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UsuarioResponseBodyDTO;

public interface IUsuarioService {

    public UsuarioResponseBodyDTO criarUsuarioService(UsuarioRequestBodyDTO usuarioRequestBodyDTO);

}
