package com.sysmap.laersonjr.socialnetwork.domain.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Comentario {

    private Usuario usuario;
    private String comentario;
    private LocalDateTime dataDeCriacao;
    private LocalDateTime dataDeAtualizacao;
    private List<Like> likes = new ArrayList<>();

    public void setDataDeCriacao(){
        this.dataDeCriacao = LocalDateTime.now();
    }

    public void setDataDeAtualizacao(){
        this.dataDeAtualizacao = LocalDateTime.now();
    }

}
