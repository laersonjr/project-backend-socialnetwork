package com.sysmap.laersonjr.socialnetwork.api.modelDTO.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UsuarioRequestBodyDTO {

    private UUID id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;

    @NotBlank
    private String apelido;

    private String urlFoto;

}