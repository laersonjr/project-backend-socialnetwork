package com.sysmap.laersonjr.socialnetwork.domain.service;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.UsuarioRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UsuarioResponseBodyDTO;
import com.sysmap.laersonjr.socialnetwork.domain.exception.UsuarioNotFoundException;
import com.sysmap.laersonjr.socialnetwork.domain.model.Usuario;
import com.sysmap.laersonjr.socialnetwork.domain.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private IModelMapperDTOConverter iModelMapperDTOConverter;

    @Override
    public UsuarioResponseBodyDTO criarUsuarioService(UsuarioRequestBodyDTO usuarioRequestBodyDTO){
        Usuario usuario = iModelMapperDTOConverter.convertToEntity(usuarioRequestBodyDTO, Usuario.class);
        usuario.setDataDeCriacao();
        usuario.setId();
        usuarioRepository.save(usuario);
        UsuarioResponseBodyDTO usuarioCriado = iModelMapperDTOConverter.convertToModelDTO(usuario, UsuarioResponseBodyDTO.class);
        return usuarioCriado;
    }
    @Override
    public Page<UsuarioResponseBodyDTO> listarUsuariosService(String apelido, Pageable pageable) {
        Page<Usuario> listaUsuarios = usuarioRepository.findByApelidoContains(apelido, pageable);
        return iModelMapperDTOConverter.convertToModelPageDTO(listaUsuarios, UsuarioResponseBodyDTO.class);
    }

    @Override
    public UsuarioResponseBodyDTO buscarUsuarioServicePeloId(UUID idUsuario) {
        return iModelMapperDTOConverter.convertToModelDTO(buscarUsuarioService(idUsuario), UsuarioResponseBodyDTO.class);
    }

    @Override
    public Usuario buscarUsuarioService(UUID idUsuario){
        return usuarioRepository.findById(idUsuario).orElseThrow(() -> new UsuarioNotFoundException());
    }

    @Override
    public Usuario buscarUsuarioServiceEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    //TODO: Acrescentar regra e DTO para atualizar apenas dados recebidos.
    @Override
    public UsuarioResponseBodyDTO atualizarUsuarioService(UUID idUsuario, UsuarioRequestBodyDTO usuarioRequestBodyDTO) {
        Usuario usuarioEncontrado = buscarUsuarioService(idUsuario);
        BeanUtils.copyProperties(usuarioRequestBodyDTO, usuarioEncontrado, "id");
        usuarioEncontrado.setDataDeAtualizacao();
        Usuario usuarioAtualizado = usuarioRepository.save(usuarioEncontrado);
        return iModelMapperDTOConverter.convertToModelDTO(usuarioAtualizado, UsuarioResponseBodyDTO.class);
    }

    @Override
    public void excluirUsuarioService(UUID idUsuario) {
        Usuario usuarioEncontrado = buscarUsuarioService(idUsuario);
        usuarioRepository.deleteById(idUsuario);
    }


}
