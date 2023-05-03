package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.UsuarioRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UsuarioResponseBodyDTO;
import com.sysmap.laersonjr.socialnetwork.domain.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IUsuarioService {

    UsuarioResponseBodyDTO criarUsuarioService(UsuarioRequestBodyDTO usuarioRequestBodyDTO);
    Page<UsuarioResponseBodyDTO> listarUsuariosService(String apelido, Pageable pageable);
    UsuarioResponseBodyDTO buscarUsuarioServicePeloId(UUID idUsuario);
    Usuario buscarUsuarioService(UUID idUsuario);
    UsuarioResponseBodyDTO atualizarUsuarioService(UUID idUsuario, UsuarioRequestBodyDTO usuarioRequestBodyDTO);
    void excluirUsuarioService(UUID idUsuario);
    Usuario buscarUsuarioServiceEmail(String email);
}
