package com.sysmap.laersonjr.socialnetwork.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "posts")
public class Post {

    private UUID id;

    private Usuario usuario;
    private String titulo;
    private String conteudo;
    private LocalDateTime dataDeCriacao;
    private LocalDateTime dataDeAtualizacao;
    private List<Comentario> comentarios = new ArrayList<>();
    private List<Like> likes = new ArrayList<>();

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public void setDataDeCriacao(){
        this.dataDeCriacao = LocalDateTime.now();
    }

    public void setDataDeAtualizacao(){
        this.dataDeAtualizacao = LocalDateTime.now();
    }

}
