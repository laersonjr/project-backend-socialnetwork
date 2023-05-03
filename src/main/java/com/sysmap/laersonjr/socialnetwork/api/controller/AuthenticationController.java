package com.sysmap.laersonjr.socialnetwork.api.controller;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.UsuarioAuthenticationDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UsuarioAuthenticatedDTO;
import com.sysmap.laersonjr.socialnetwork.core.security.TokenProvider;
import com.sysmap.laersonjr.socialnetwork.domain.model.Usuario;
import com.sysmap.laersonjr.socialnetwork.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenProvider tokenProvider;

    //TODO: Refatorar controller com principio de responsabilidade unica.

    @PostMapping("/login")
    public ResponseEntity<UsuarioAuthenticatedDTO> authenticateUser(@RequestBody UsuarioAuthenticationDTO login) {

        Usuario usuario = usuarioService.buscarUsuarioServiceEmail(login.getEmail());

        if(usuario == null) {
            return ResponseEntity.badRequest().body(new UsuarioAuthenticatedDTO("Usuário não encontrado com o email: " + login.getEmail()));
        }

        if(!usuario.getSenha().equals(login.getSenha())) {
            return ResponseEntity.badRequest().body(new UsuarioAuthenticatedDTO("Senha incorreta"));
        }

        String token = tokenProvider.generateToken(login.getEmail());

        return ResponseEntity.ok(new UsuarioAuthenticatedDTO(token));
    }

}
