package com.sysmap.laersonjr.socialnetwork.domain.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;

    @NotBlank
    private String apelido;

    @Field("url_foto")
    private String urlFoto;

    @Field("data_de_criacao")
    private LocalDateTime dataDeCriacao;

    @Field("data_de_atualizacao")
    private LocalDateTime dataDeAtualizacao;

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
