package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.UsuarioRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UsuarioResponseBodyDTO;
import com.sysmap.laersonjr.socialnetwork.domain.model.Usuario;
import com.sysmap.laersonjr.socialnetwork.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private IModelMapperDTOConverter iModelMapperDTOConverter;

    public UsuarioResponseBodyDTO criarUsuarioService(UsuarioRequestBodyDTO usuarioRequestBodyDTO){
        Usuario usuario = iModelMapperDTOConverter.convertToEntity(usuarioRequestBodyDTO, Usuario.class);
        usuario.setDataDeCriacao();
        usuario.setId();
        usuarioRepository.save(usuario);
        UsuarioResponseBodyDTO usuarioCriado = iModelMapperDTOConverter.convertToModelDTO(usuario, UsuarioResponseBodyDTO.class);
        return usuarioCriado;
    }


}
