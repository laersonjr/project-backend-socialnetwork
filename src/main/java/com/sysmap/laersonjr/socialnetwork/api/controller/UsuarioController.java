package com.sysmap.laersonjr.socialnetwork.api.controller;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.UsuarioRequestBodyDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UsuarioResponseBodyDTO;
import com.sysmap.laersonjr.socialnetwork.domain.model.Usuario;
import com.sysmap.laersonjr.socialnetwork.domain.repository.UsuarioRepository;
import com.sysmap.laersonjr.socialnetwork.domain.service.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private IUsuarioService iUsuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseBodyDTO> criarUsuario(@Valid @RequestBody UsuarioRequestBodyDTO usuarioRequestBodyDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(iUsuarioService.criarUsuarioService(usuarioRequestBodyDTO));
    }

    @GetMapping
    public Page<UsuarioResponseBodyDTO> listarUsuarios(@RequestParam(required = false, defaultValue = "") String apelido, Pageable pageable) {
        return iUsuarioService.listarUsuariosService(apelido, pageable);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponseBodyDTO> buscarUsuarioPeloId(@PathVariable UUID idUsuario){
        return ResponseEntity.ok(iUsuarioService.buscarUsuarioServicePeloId(idUsuario));
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponseBodyDTO> autilizarUsuario(@PathVariable UUID idUsuario, @Valid @RequestBody UsuarioRequestBodyDTO usuarioRequestBodyDTO){
        return ResponseEntity.ok(iUsuarioService.atualizarUsuarioService(idUsuario, usuarioRequestBodyDTO));
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable UUID idUsuario){
        iUsuarioService.excluirUsuarioService(idUsuario);
        return ResponseEntity.noContent().build();
    }

}
