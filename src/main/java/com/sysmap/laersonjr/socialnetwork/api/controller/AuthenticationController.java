package com.sysmap.laersonjr.socialnetwork.api.controller;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.UserAuthenticationDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UserAuthenticatedDTO;
import com.sysmap.laersonjr.socialnetwork.core.security.TokenProvider;
import com.sysmap.laersonjr.socialnetwork.domain.model.User;
import com.sysmap.laersonjr.socialnetwork.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private UserService usuarioService;

    @Autowired
    private TokenProvider tokenProvider;

    //TODO: Refatorar controller com principio de responsabilidade unica.

    @PostMapping("/login")
    public ResponseEntity<UserAuthenticatedDTO> authenticateUser(@RequestBody UserAuthenticationDTO login) {

        User user = usuarioService.findUserByEmailService(login.getEmail());

        if(user == null) {
            return ResponseEntity.badRequest().body(new UserAuthenticatedDTO("Usuário não encontrado com o email: " + login.getEmail()));
        }

        if(!user.getPassword().equals(login.getPassword())) {
            return ResponseEntity.badRequest().body(new UserAuthenticatedDTO("Senha incorreta"));
        }

        String token = tokenProvider.generateToken(login.getEmail());

        return ResponseEntity.ok(new UserAuthenticatedDTO(token));
    }

}
