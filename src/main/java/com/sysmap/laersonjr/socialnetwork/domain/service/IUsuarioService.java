package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.UsuarioRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UsuarioResponseBodyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUsuarioService {

    public UsuarioResponseBodyDTO criarUsuarioService(UsuarioRequestBodyDTO usuarioRequestBodyDTO);
    public Page<UsuarioResponseBodyDTO> listarUsuariosService(String apelido, Pageable pageable);

}
