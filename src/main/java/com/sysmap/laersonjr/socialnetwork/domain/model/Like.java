package com.sysmap.laersonjr.socialnetwork.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Like {

    private Usuario usuario;
    private LocalDateTime dataDeCriacao;

    public void setDataDeCriacao(){
        this.dataDeCriacao = LocalDateTime.now();
    }


}
