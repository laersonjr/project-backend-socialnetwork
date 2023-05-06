package com.sysmap.laersonjr.socialnetwork.api.modelDTO.input;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UserResumeCommentDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CommentRequestBodyDTO {

    private UUID id;

    @NotBlank
    private String commentary;

}
