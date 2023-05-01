package com.sysmap.laersonjr.socialnetwork.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "usuarios")
public class Usuario {

    @EqualsAndHashCode.Include
    @Id
    private UUID id;
    private String email;
    private String senha;
    private String apelido;
    @Field("url_foto")
    private String urlFoto;
    @CreatedDate
    @Field("data_de_criacao")
    private LocalDateTime dataDeCriacao;
    @LastModifiedDate
    @Field("data_de_atualizacao")
    private LocalDateTime dataAtualizacao;

    public void setId() {
        this.id = UUID.randomUUID();
    }


}
