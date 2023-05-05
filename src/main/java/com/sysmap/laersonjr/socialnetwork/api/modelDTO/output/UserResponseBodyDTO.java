package com.sysmap.laersonjr.socialnetwork.api.modelDTO.output;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UserResponseBodyDTO {

    private UUID id;
    private String email;
    private String apelido;
    private LocalDateTime dataDeCriacao;

}
