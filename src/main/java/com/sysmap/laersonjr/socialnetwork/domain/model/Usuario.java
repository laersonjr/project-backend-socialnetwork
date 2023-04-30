package com.sysmap.laersonjr.socialnetwork.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @EqualsAndHashCode.Include
    private UUID id;
    private String email;
    private String senha;
    private String apelido;
    private String urlFoto;
    private LocalDateTime dataDeCriacao;

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public void setDataDeCriacao() {
        this.dataDeCriacao = LocalDateTime.now();
    }


}
