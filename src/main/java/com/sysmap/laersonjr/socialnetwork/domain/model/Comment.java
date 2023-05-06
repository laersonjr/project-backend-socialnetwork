package com.sysmap.laersonjr.socialnetwork.domain.model;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UserResumeCommentDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Comment {

    @EqualsAndHashCode.Include
    @Id
    private UUID id;

    private UserResumeCommentDTO user;

    @NotBlank
    private String commentary;

    @Field("created_date")
    private LocalDateTime createdDate;

    @Field("updated_date")
    private LocalDateTime updatedDate;

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
