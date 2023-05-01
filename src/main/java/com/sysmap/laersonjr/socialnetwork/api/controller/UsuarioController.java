package com.sysmap.laersonjr.socialnetwork.api.controller;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.UsuarioRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UsuarioResponseBodyDTO;
import com.sysmap.laersonjr.socialnetwork.domain.model.Usuario;
import com.sysmap.laersonjr.socialnetwork.domain.repository.UsuarioRepository;
import com.sysmap.laersonjr.socialnetwork.domain.service.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private IUsuarioService iUsuarioService;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseBodyDTO> criarUsuario(@Valid @RequestBody UsuarioRequestBodyDTO usuarioRequestBodyDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(iUsuarioService.criarUsuarioService(usuarioRequestBodyDTO));
    }

}
