package com.sysmap.laersonjr.socialnetwork.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserAuthenticationDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
