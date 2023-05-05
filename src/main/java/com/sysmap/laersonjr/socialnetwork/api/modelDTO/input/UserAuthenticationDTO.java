package com.sysmap.laersonjr.socialnetwork.api.modelDTO.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserAuthenticationDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
