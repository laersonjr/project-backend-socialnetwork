package com.sysmap.laersonjr.socialnetwork.domain.model;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UserResumePostDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "posts")
public class Post {

    @EqualsAndHashCode.Include
    @Id
    private UUID id;

    private UserResumePostDTO user;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @Field("created_date")
    private LocalDateTime createdDate;

    @Field("updated_date")
    private LocalDateTime updatedDate;

    private List<Comment> comments = new ArrayList<>();

    private List<Like> likes = new ArrayList<>();

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public void setCreatedDate() {
        this.createdDate = LocalDateTime.now();
    }

    public void setUpdatedDate() {
        this.updatedDate = LocalDateTime.now();
    }

}
