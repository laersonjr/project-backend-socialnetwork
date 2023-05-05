package com.sysmap.laersonjr.socialnetwork.api.controller;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.input.UserAuthenticationDTO;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UserAuthenticatedDTO;
import com.sysmap.laersonjr.socialnetwork.domain.service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {


    @Autowired
    private IAuthenticationService iAuthenticationService;

    @PostMapping("/login")
    public ResponseEntity<UserAuthenticatedDTO> authenticateUser(@RequestBody UserAuthenticationDTO login) {
        return ResponseEntity.ok(new UserAuthenticatedDTO(iAuthenticationService.authenticateUser(login)));
    }

}
